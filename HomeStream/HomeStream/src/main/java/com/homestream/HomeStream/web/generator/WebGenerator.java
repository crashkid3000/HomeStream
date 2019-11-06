package com.homestream.HomeStream.web.generator;

import com.homestream.HomeStream.main.assets.ScriptEngine;

import java.io.File;
import java.io.IOException;

public class WebGenerator
{
    /**
     * Class to generate an dynamic site by '.script' File
     *
     * @author S. Schulze
     * @last_update 2.11.19
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

        return null;
    }

    private String write()
    {
        String out = "";

        out += "<!DOCTYPE html>";
        out += "<html>";
        out += "<head>";

        return out;
    }

    public String decrypt(String key)
    {
        String[] list = script.getLine(key);
        String out = "";

        for (String element : list)
        {
            if(key == "title:" || key == "title")
            {
                out += "<title>";
                out += getVelue(element, "text");
                out += "</title>";
            }
            if(key == "include:" || key == "include")
            {

            }
            if(key == "content:" || key == "content")
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
}
