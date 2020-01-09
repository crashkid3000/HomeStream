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

    public HTML(String file, boolean statics, boolean music, boolean video) throws IOException
    {
        if(file.endsWith(".script"))
        {
            data = generator.generate(file,statics, music, video);
            tags = generator.getTags();
        }
        else throw new FileEndingException(".script");
    }

    public void prepare(List<? extends MediaEntity> Edata, String search, boolean music)
    {
        counter = 0;

        isprep = true;
        prep = data;
        elements = "<div class=\"list BOTTOM_SMAL\">";

        if(search.equals("music")) this.search = Lang.MUSIC;
        else if(search.equals("image")) this.search = Lang.IMAGE;
        else if(search.equals("film")) this.search = Lang.VIDEO;
        else if(search.equals("home")) this.search = Lang.HOME;
        else this.search = Lang.CONTENT_TITLE_SEARCH_RESULT + " '" + search + "'";

        for (int i = 0; i < Edata.size(); i++)
        {
            if(counter == 5)
            {
                counter = 0;
                elements += "</div><div class=\"list BOTTOM_SMAL\">";
            }

            if(Edata.get(i).getClass().toString().equals("class com.homestream.HomeStream.entity.MusicEntity")) elements += "\n" +
                    "<div class=\"cover\">\n" +
                    "<img name=\"cover\" src=\"data/" + Edata.get(i).getThumbnailName() + "\">\n" +
                    "<div name=\"coverText\">\n" +
                    "<h2 name=\"coverText\">" + Edata.get(i).getName() + "</h2>\n" +
                    "<h2><a><i name=\"audio\" id=\"" + Edata.get(i).getFileName() + "\" title=\"" + Edata.get(i).getName() + "\" class=\"fa fa-fw fa-play mediaButton\" id=\"user\"></i></a>\t<a name=\"audioPlayer\" data=\"" + Edata.get(i).getId() + "\"><i class=\"fa fa-fw fa-youtube-play mediaButton\" id=\"user\"></i></a></h2>\n" +
                    "</div>\n" +
                    "</div>";
            else if(Edata.get(i).getClass().toString().equals("class com.homestream.HomeStream.entity.FilmEntity")) elements += "\n" +
                    "<div name=\"video\" id=\"" + Edata.get(i).getFileName() + "\" title=\"" + Edata.get(i).getName() + "\" class=\"cover\">\n" +
                    "<img name=\"cover\" src=\"data/" + Edata.get(i).getThumbnailName() + "\">\n" +
                    "<div name=\"coverText\">\n" +
                    "<h2 name=\"coverText\">" + Edata.get(i).getName() + "</h2>\n" +
                    "</div>\n" +
                    "</div></a>";
            else elements += "\n" +
                    "<div name=\"img\" id=\"" + Edata.get(i).getFileName() + "\" title=\"" + Edata.get(i).getName() + "\" class=\"cover\">\n" +
                    "<img name=\"cover\" src=\"data/" + Edata.get(i).getFileName() + "\">\n" +
                    "<div name=\"coverText\">\n" +
                    "<h2 name=\"coverText\">" + Edata.get(i).getName() + "</h2>\n" +
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

        out = replaceAll(out,"@MORE", Lang.MORE);
        out = replaceAll(out,"@HOME", Lang.HOME);
        out = replaceAll(out,"@FILTER", Lang.FILTER);
        out = replaceAll(out,"@VIDEO", Lang.VIDEO);
        out = replaceAll(out,"@IMAGE", Lang.IMAGE);
        out = replaceAll(out,"@MUSIC", Lang.MUSIC);

        out = replaceAll(out,"@SEARCH", Lang.SEARCH);
        out = replaceAll(out,"@LOGIN", Lang.LOGIN);
        out = replaceAll(out,"@QUIT", Lang.QUIT);
        out = replaceAll(out,"@REGISTER", Lang.REGISTER);
        out = replaceAll(out,"@USERNAME", Lang.USERNAME);
        out = replaceAll(out,"@PASSWORD", Lang.PASSWORD);

        out = replaceAll(out,"@UPDATE_LOG", Lang.UPDATE_LOG);
        out = replaceAll(out,"@CLICK_TO_CLOSE", Lang.CLICK_TO_CLOSE);

        out = replaceAll(out,"@TITLE_NAME_SEARCH", Lang.TITLE_NAME_SEARCH);
        out = replaceAll(out,"@PLAYLIST_NAME_SEARCH", Lang.PLAYLIST_NAME_SEARCH);
        out = replaceAll(out,"@ARTIST_ACTOR_SEARCH", Lang.ARTIST_ACTOR_SEARCH);
        out = replaceAll(out,"@OWNER_SEARCH", Lang.OWNER_SEARCH);
        out = replaceAll(out,"@RELEASE_SEARCH", Lang.RELEASE_SEARCH);
        out = replaceAll(out,"@GENRE_SEARCH", Lang.GENRE_SEARCH);
        out = replaceAll(out,"@TAGS_SEARCH", Lang.TAGS_SEARCH);

        out = replaceAll(out,"@TITLE", Properties.TITLE);
        out = replaceAll(out,"@MOTTO", Properties.MOTTO[(int) ((Math.random() * 10) % Properties.MOTTO.length)]);

        for(String item : tags)out = replaceFirst(out, item);

        return out;
    }

    public String get(MediaEntity me)
    {
        String out = data;

        if(isprep) out = replaceAll(prep,"@ELEMENTS", elements);

        out = replaceAll(out,"@TITLE", Properties.TITLE);
        out = replaceAll(out,"@MOTTO", Properties.MOTTO[(int) ((Math.random() * 10) % Properties.MOTTO.length)]);

        out = replaceAll(out,"@COVER_TN", me.getThumbnailName());
        out = replaceAll(out,"@COVER_TITLE", me.getName());
        out = replaceAll(out,"@DATA", me.getFileName());

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

    public String get(MediaEntity me, List<MediaEntity> playlist)
    {
        String out = data;
        String list = "";

        for (int i = 0; i < playlist.size() && i < 21; i++) list += "<a href=\"audio_" + playlist.get(i).getId() + "\"><li>" + playlist.get(i).getName() + "</li></a>";

        if(isprep) out = replaceAll(prep,"@ELEMENTS", elements);

        out = replaceAll(out, "@PLAYLIST", list);

        out = replaceAll(out,"@TITLE", Properties.TITLE);
        out = replaceAll(out,"@MOTTO", Properties.MOTTO[(int) ((Math.random() * 10) % Properties.MOTTO.length)]);

        out = replaceAll(out,"@COVER_TN", me.getThumbnailName());
        out = replaceAll(out,"@COVER_TITLE", me.getName());
        out = replaceAll(out,"@DATA", me.getFileName());

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

        switch (target)
        {
            case "#LATEST_UPLOADS":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_LAST_UPLOADS).replaceFirst("@CONTENT_ID", Properties.CONTENT_TITLE_LAST_UPLOADS_ID);
            case "#LATEST_STREAMS":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_LAST_STREAMS).replaceFirst("@CONTENT_ID", Properties.CONTENT_TITLE_LAST_STREAMS_ID);
            case "#FAVORITES":
                return source.replaceFirst("@CONTENT_TITLE", Lang.CONTENT_TITLE_FAVORITES).replaceFirst("@CONTENT_ID", Properties.CONTENT_TITLE_FAVORITES_ID);
            case "#SEARCH_RESULT":
                return source.replaceFirst("@CONTENT_TITLE", search).replaceFirst("@CONTENT_ID", Properties.CONTENT_TITLE_SEARCH_RESULT_ID);
            default:
                return source;
        }
    }
}
