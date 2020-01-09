package com.homestream.HomeStream.main.assets.property;

import java.io.IOException;

public class Lang
{
    /**
     * Language Properties
     *
     * @author S. Schulze
     * @last_update 28.11.19
     */

    private static LangLoader loader = new LangLoader();


    public static final String MORE = loader.getValue("MORE");
    public static final String FILTER = loader.getValue("FILTER");

    public static final String VIDEO = loader.getValue("VIDEO");
    public static final String IMAGE = loader.getValue("IMAGE");
    public static final String MUSIC = loader.getValue("MUSIC");
    public static final String SEARCH = loader.getValue("SEARCH");
    public static final String HOME = loader.getValue("HOME");

    public static final String LOGIN = loader.getValue("LOGIN");
    public static final String QUIT = loader.getValue("QUIT");
    public static final String REGISTER = loader.getValue("REGISTER");
    public static final String USERNAME = loader.getValue("USERNAME");
    public static final String PASSWORD = loader.getValue("PASSWORD");

    public static final String CLICK_TO_CLOSE = loader.getValue("CLICK_TO_CLOSE");

    public static final String TITLE_NAME_SEARCH = loader.getValue("TITLE_NAME_SEARCH");
    public static final String PLAYLIST_NAME_SEARCH = loader.getValue("PLAYLIST_NAME_SEARCH");
    public static final String ARTIST_ACTOR_SEARCH = loader.getValue("ARTIST_ACTOR_SEARCH");
    public static final String OWNER_SEARCH = loader.getValue("OWNER_SEARCH");
    public static final String RELEASE_SEARCH = loader.getValue("RELEASE_SEARCH");
    public static final String GENRE_SEARCH = loader.getValue("GENRE_SEARCH");
    public static final String TAGS_SEARCH = loader.getValue("TAGS_SEARCH");

    public static final String CONTENT_TITLE_LAST_UPLOADS = loader.getValue("CONTENT_TITLE_LAST_UPLOADS");
    public static final String CONTENT_TITLE_LAST_STREAMS = loader.getValue("CONTENT_TITLE_LAST_STREAMS");
    public static final String CONTENT_TITLE_FAVORITES = loader.getValue("CONTENT_TITLE_FAVORITES");
    public static final String CONTENT_TITLE_SEARCH_RESULT = loader.getValue("CONTENT_TITLE_SEARCH_RESULT");

    public static final String UPDATE_LOG = loader.getValue("UPDATE_LOG");
}
