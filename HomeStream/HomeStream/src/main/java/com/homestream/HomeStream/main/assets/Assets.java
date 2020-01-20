package com.homestream.HomeStream.main.assets;

import com.homestream.HomeStream.main.assets.property.Lang;

import java.util.LinkedList;
import java.util.List;

public class Assets
{
    /**
     * Main Class to Store Asset-Functions
     */

    /**
     * Function to Check File Name contains File Types
     * @param fileName
     * @param type
     * @param types
     * @return
     */
    public static boolean checkFileType(String fileName, String type, String... types)
    {
        if(fileName.endsWith(type.replaceFirst(".",""))) return true;
        for (String s : types) if(fileName.endsWith(s.replaceFirst(".",""))) return true;
        return false;
    }

    /**
     * Function to shuffle a List
     * @param list
     * @return
     */
    public static List randomizeList(List list)
    {
        List temp = new LinkedList<>();

        while(list.size() > 0)
        {
            int i = (int)(Math.random() * 10) % list.size();
            temp.add(list.get(i));
            list.remove(i);
        }

        return temp;
    }

    /**
     * Simplified Function to replace all Targets by Value in Source
     * @param source
     * @param target
     * @param value
     * @return
     */
    public static String replaceAll(String source, String target, String value)
    {
        return source.replaceAll(target, value);
    }

    /**
     * Function to Decrypt Search Query from extended Search
     * @param query
     * @return
     */
    public static String decryptQuery(String query)
    {
        String[] params = query.split("§");
        String out = "";

        out += Lang.EXT_SEARCH;
        out += "<br>";

        for(int i = 1; i < params.length; i++)
        {
            out += params[i].replace(",",": ");
            out += "<br>";
        }

        out = out.replace("TITLE", Lang.TITLE_NAME_SEARCH);
        out = out.replace("PLAYLIST", Lang.PLAYLIST_NAME_SEARCH);
        out = out.replace("ARTIST", Lang.ARTIST_ACTOR_SEARCH);
        out = out.replace("OWNER", Lang.OWNER_SEARCH);
        out = out.replace("RELEASE", Lang.RELEASE_SEARCH);
        out = out.replace("GENRE", Lang.GENRE_SEARCH);
        out = out.replace("TAGS", Lang.TAGS_SEARCH);

        return out;
    }
}
