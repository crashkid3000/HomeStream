package com.homestream.HomeStream.web.generator;

import com.homestream.HomeStream.main.assets.property.Properties;
import com.homestream.HomeStream.main.assets.ScriptEngine;
import com.homestream.HomeStream.main.exception.ScriptFormatException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WebGenerator
{
    /**
     * Class to generate an dynamic site by '.script' File
     *
     * @author S. Schulze
     * @last_update 6.11.19
     */

    boolean firstElement = true;

    ArrayList<String> tags = new ArrayList<>();
    ScriptEngine script = new ScriptEngine();


    public String generate(String scriptFile)
    {
        File file = new File(scriptFile);
        try {

            script.setScript(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String out = null;
        try {
            out = write();
        } catch (ScriptFormatException e) {
            e.printStackTrace();
        }
        return out;
    }

    private String write() throws ScriptFormatException {
        String out = "";

        out += "<!DOCTYPE html>\n";
        out += "<html>\n";
        out += "<head>\n";
        out += decrypt("title:", "include:");
        out += getJS("#MAIN_JS");
        out += "</head>\n";
        out += "<body>\n";
        out += getContent("#NAVIGATION");
        out += getContent("#HEAD");
        out += decrypt("content:");
        out += "</body>\n";
        out += "</html>\n";

        return out;
    }

    private String decrypt(String key, String... keys) {
        String[] keyList = new String[keys.length + 1];
        String[] lines;

        keyList[0] = key;
        if(keys != null)for( int i = 1; i < keyList.length; i++) keyList[i] = keys[i - 1];

        String out = "";
        for(String item : keyList)
        {
            try
            {
                lines = script.getLine(item);
                for(String i : lines)
                {
                    if(item.equals("title:") && i.equals("#TITLE")) out += "<title>" + Properties.TITLE + "</title>\n";
                    else if(item.equals("include:"))
                    {
                        if(i.endsWith("CSS")) out += "<link rel=\"stylesheet\" href=\"" + getCSS(i) + "\">\n";
                    }
                    else if(item.equals("content:")) if(i.startsWith("#"))
                        {
                            tags.add(i);

                            if(firstElement)
                            {
                                out += "<div class=\"box boxFirst\">";
                                firstElement = false;
                            }
                            else out += "<div class=\"box\">";

                            out += getContent(i);

                            out += "</div>";
                        }
                }
            }
            catch (IOException e)
            {

            }
        }
        return out;
    }

    private String getVelue(String element, String parameter)
    {
        String[] items;
        if(element.startsWith(parameter))
        {
            items = element.split("=");
            return items[1].replace("'","");
        }

        return null;
    }

    public String[] getTags()
    {
        String[] out = new String[tags.size()];

        for(int i = 0; i < out.length; i++) out[i] = tags.get(i);

        return out;
    }

    private String getCSS(String item)
    {
        byte index;
        if(item.startsWith("#DARK")) index = Properties.DARK;
        else index = Properties.LIGHT;

        if(item.endsWith("ICON_CSS")) return Properties.ICONS_CSS[index];
        else if(item.endsWith("MAIN_CSS")) return Properties.MAIN_CSS[index];
        else if(item.endsWith("COLOR_CSS")) return Properties.COLOR_CSS[index];
        else if(item.endsWith("CONTENT_CSS")) return Properties.CONTENT_CSS[index];
        else if(item.endsWith("NAVIGATION_CSS")) return Properties.NAVIGATION_CSS[index];
        else if(item.endsWith("BACKGROUND_CSS")) return Properties.BACKGROUND_CSS[index];
        else return null;
    }

    private String getJS(String item)
{
    if(item.endsWith("MAIN_JS")) return "<script text=\"text/javascript\" src=\"" + Properties.MAIN_JS + "\">\n</script>";
    else return null;
}

    private String getContent(String item) throws ScriptFormatException {
        switch (item)
        {
            case "#HEAD":
                return Properties.CONTENT_TITLE[0].get();
            case "#NAVIGATION":
                return Properties.CONTENT_NAVIGATION[0].get();
            case "#LATEST_UPLOADS":
            case "#LATEST_STREAMS":
            case "#FAVORITES":
            case "#SEARCH_RESULT":
                return Properties.CONTENT_ELEMENT[0].get();
            default:
                throw new ScriptFormatException(item);
        }
    }
}
