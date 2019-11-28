package com.homestream.HomeStream.main.assets.property;

import java.io.IOException;

public class Lang
{
    private static LangLoader loader = new LangLoader();


    public static final String MORE = loader.getValue("MORE");
    public static final String FILTER = loader.getValue("FILTER");

    public static final String VIDEO = loader.getValue("VIDEO");
    public static final String IMAGE = loader.getValue("IMAGE");
    public static final String MUSIC = loader.getValue("MUSIC");
    public static final String SEARCH = loader.getValue("SEARCH");

    public static final String CONTENT_TITLE_LAST_UPLOADS = loader.getValue("CONTENT_TITLE_LAST_UPLOADS");
    public static final String CONTENT_TITLE_LAST_STREAMS = loader.getValue("CONTENT_TITLE_LAST_STREAMS");
    public static final String CONTENT_TITLE_FAVORITES = loader.getValue("CONTENT_TITLE_FAVORITES");
    public static final String CONTENT_TITLE_SEARCH_RESULT = loader.getValue("CONTENT_TITLE_SEARCH_RESULT");
}
