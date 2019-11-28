package com.homestream.HomeStream.web.assets;

import com.homestream.HomeStream.main.assets.property.Lang;
import com.homestream.HomeStream.main.assets.property.Properties;
import com.homestream.HomeStream.main.exception.FileEndingException;
import com.homestream.HomeStream.web.generator.WebGenerator;

import java.io.*;

public class HTML
{
    /**
     * Class to store HTML
     *
     * @author S. Schulze
     * @last_update 5.11.19
     */

    String data;
    String tags[];
    WebGenerator generator = new WebGenerator();

    public HTML(String file) throws IOException
    {
        if(file.endsWith(".script"))
        {
            data = generator.generate(file);
            tags = generator.getTags();
        }
        else throw new FileEndingException(".script");
    }

    public String get()
    {
        String out = data;

        out = replaceAll(out,"@TITLE", Properties.TITLE);
        out = replaceAll(out,"@MOTTO", Properties.MOTTO[(int) ((Math.random() * 10) % Properties.MOTTO.length)]);

        out = replaceAll(out,"@MORE", Lang.MORE);
        out = replaceAll(out,"@FILTER", Lang.FILTER);
        out = replaceAll(out,"@VIDEO", Lang.VIDEO);
        out = replaceAll(out,"@IMAGE", Lang.IMAGE);
        out = replaceAll(out,"@MUSIC", Lang.MUSIC);
        out = replaceAll(out,"@SEARCH", Lang.SEARCH);

        for(String item : tags)out = replaceFirst(out, item);

        return out;
    }

    private String replaceAll(String source, String target, String value)
    {
        return source.replaceAll(target, value);
    }

    private String replaceFirst(String source, String target)
    {
        System.out.println(target);

        switch (target)
        {
            case "#LATEST_UPLOADS":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_LAST_UPLOADS);
            case "#LATEST_STREAMS":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_LAST_STREAMS);
            case "#FAVORITES":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_FAVORITES);
            case "#SEARCH_RESULT":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_SEARCH_RESULT);
            default:
                return source;
        }
    }
}
