package com.homestream.HomeStream.web.controller;

public class RequestHandler
{
    // General
    public final byte LAST_UPLOAD = 0;
    public final byte LAST_UPLOAD_VIDEO = 1;
    public final byte LAST_UPLOAD_IMAGE = 2;
    public final byte LAST_UPLOAD_MUSIC = 3;

    public final byte LAST_STREAM = 4;
    public final byte LAST_STREAM_VIDEO = 5;
    public final byte LAST_STREAM_IMAGE = 6;
    public final byte LAST_STREAM_MUSIC = 7;

    public final byte FAVORITE = 8;
    public final byte FAVORITE_VIDEO = 9;
    public final byte FAVORITE_IMAGE = 10;
    public final byte FAVORITE_MUSIC = 11;

    // User


    public final byte ELSE = 127;

    public String requestDB(Byte requestType, byte count)
    {
        return null;
    }
}
