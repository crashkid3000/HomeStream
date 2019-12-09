package com.homestream.HomeStream.web.generator;

import com.homestream.HomeStream.main.assets.Loader;

import java.io.File;
import java.io.IOException;

public class ContentGenerator
{
    Loader loader = new Loader();

    String rowElement;

    public ContentGenerator(boolean isVideo)
    {
        try {
            rowElement = loader.LoadFileToString(new File("./res/template/element.temp"));

            if(isVideo) rowElement = rowElement.replace("@ELEMENT_INFO","<h4>@ELEMENT_CREATER</h4>");
            else rowElement = rowElement.replace("@ELEMENT_INFO","<h4>@ELEMENT_CREATER</h4>\n<p>@ELEMENT_TITLE</p>");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
