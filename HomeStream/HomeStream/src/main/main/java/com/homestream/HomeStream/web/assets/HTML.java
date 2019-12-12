package com.homestream.HomeStream.web.assets;

import com.homestream.HomeStream.entity.MediaEntity;
import com.homestream.HomeStream.main.assets.property.Lang;
import com.homestream.HomeStream.main.assets.property.Properties;
import com.homestream.HomeStream.main.exception.FileEndingException;
import com.homestream.HomeStream.web.generator.WebGenerator;

import java.io.*;
import java.util.List;

public class HTML
{
    /**
     * Class to store HTML
     *
     * @author S. Schulze
     * @last_update 5.11.19
     */

    String search;
    String data;
    String prep;

    String elements;

    String tags[];
    int counter = 0;
    boolean isprep = false;

    WebGenerator generator = new WebGenerator();

    public HTML(String file, boolean statics, boolean music) throws IOException
    {
        if(file.endsWith(".script"))
        {
            data = generator.generate(file,statics, music);
            tags = generator.getTags();
        }
        else throw new FileEndingException(".script");
    }

    public void prepare(List<MediaEntity> Edata, String search)
    {
        isprep = true;
        prep = data;
        elements = "<div class=\"list BOTTOM_SMAL\">";

        if(search.equals("music")) this.search = Lang.MUSIC;
        else if(search.equals("image")) this.search = Lang.IMAGE;
        else if(search.equals("film")) this.search = Lang.VIDEO;
        else this.search = search;

        for (MediaEntity me : Edata)
        {
            if(counter == 5)
            {
                counter = 0;
                elements += "</div><div class=\"list BOTTOM_SMAL\">";
            }

            elements += "\n" +
                    "<a href=\"/player_" + me.getId() + "\"><div class=\"cover\">\n" +
                    "<img name=\"cover\" src=\"data/" + me.getThumbnailName() + "\">\n" +
                    "<div name=\"coverText\">\n" +
                    "<h2 name=\"coverText\">" + me.getName() + "</h2>\n" +
                    "</div>\n" +
                    "</div></a>";

            counter++;
        }

        elements += "</div>";
    }

    public String get()
    {
        String out = data;

        if(isprep) out = replaceAll(prep,"@ELEMENTS", elements);

        out = replaceAll(out,"@TITLE", Properties.TITLE);
        out = replaceAll(out,"@MOTTO", Properties.MOTTO[(int) ((Math.random() * 10) % Properties.MOTTO.length)]);

        out = replaceAll(out,"@MORE", Lang.MORE);
        out = replaceAll(out,"@FILTER", Lang.FILTER);
        out = replaceAll(out,"@VIDEO", Lang.VIDEO);
        out = replaceAll(out,"@IMAGE", Lang.IMAGE);
        out = replaceAll(out,"@MUSIC", Lang.MUSIC);
        out = replaceAll(out,"@SEARCH", Lang.SEARCH);
        out = replaceAll(out,"@UPDATE_LOG", Lang.UPDATE_LOG);

        for(String item : tags)out = replaceFirst(out, item);

        return out;
    }

    public String get(MediaEntity me)
    {
        String out = data;

        if(isprep) out = replaceAll(prep,"@ELEMENTS", elements);

        out = replaceAll(out,"@TITLE", Properties.TITLE);
        out = replaceAll(out,"@MOTTO", Properties.MOTTO[(int) ((Math.random() * 10) % Properties.MOTTO.length)]);

        out = replaceAll(out, "@COVER_TN", me.getThumbnailName());
        out = replaceAll(out, "@COVER_TITLE", me.getName());
        out = replaceAll(out, "@DATA", me.getFileName());

        out = replaceAll(out,"@MORE", Lang.MORE);
        out = replaceAll(out,"@FILTER", Lang.FILTER);
        out = replaceAll(out,"@VIDEO", Lang.VIDEO);
        out = replaceAll(out,"@IMAGE", Lang.IMAGE);
        out = replaceAll(out,"@MUSIC", Lang.MUSIC);
        out = replaceAll(out,"@SEARCH", Lang.SEARCH);
        out = replaceAll(out,"@UPDATE_LOG", Lang.UPDATE_LOG);

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
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_LAST_UPLOADS).replaceFirst("@CONTENT_ID", Properties.CONTENT_TITLE_LAST_UPLOADS_ID);
            case "#LATEST_STREAMS":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_LAST_STREAMS).replaceFirst("@CONTENT_ID", Properties.CONTENT_TITLE_LAST_STREAMS_ID);
            case "#FAVORITES":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_FAVORITES).replaceFirst("@CONTENT_ID", Properties.CONTENT_TITLE_FAVORITES_ID);
            case "#SEARCH_RESULT":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_SEARCH_RESULT + ": " + search).replaceFirst("@CONTENT_ID", Properties.CONTENT_TITLE_SEARCH_RESULT_ID);
            default:
                return source;
        }
    }
}
