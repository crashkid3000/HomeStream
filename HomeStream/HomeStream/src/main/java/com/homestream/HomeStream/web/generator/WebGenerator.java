package com.homestream.HomeStream.web.generator;

import com.homestream.HomeStream.main.assets.Properties;
import com.homestream.HomeStream.main.assets.ScriptEngine;

import java.io.File;
import java.io.IOException;

public class WebGenerator
{
    /**
     * Class to generate an dynamic site by '.script' File
     *
     * @author S. Schulze
     * @last_update 6.11.19
     */

    ScriptEngine script = new ScriptEngine();


    public String generate(String scriptFile)
    {
        File file = new File(scriptFile);
        try {

            script.setScript(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return write();
    }

    private String write()
    {
        String out = "";

        out += "<!DOCTYPE html>\n";
        out += "<html>\n";
        out += "<head>\n";
        out += decrypt("title:", "include:");
        out += "</head>\n";
        out += "<body>\n";
        out += decrypt("content:");
        out += "</body>\n";
        out += "</html>\n";

        return out;
    }

    private String decrypt(String key, String... keys)
    {
        String[] keyList = new String[keys.length + 1];
        String[] lines;

        keyList[0] = key;
        if(keys != null)for( int i = 1; i < keyList.length; i++) keyList[i] = keys[i - 1];

        String out = "";
        for(String item : keyList)
        {
            lines = script.getLine(item);
            for(String i : lines)
            {
                if(item.equals("title:") && i.equals("#TITLE")) out += "<title>" + Properties.TITLE + "</title>\n";
                else if(item.equals("include:"))
                {
                    if(i.endsWith("CSS")) out += "<link rel=\"stylesheet\" href=\"" + getCSS(i) + "\">\n";
                    else if(i.endsWith("JS"));
                    else;
                }
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
}
