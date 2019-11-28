package com.homestream.HomeStream.main.exception;

import java.io.IOException;

public class FileEndingException extends IOException
{
    public FileEndingException(String requestedEnding, String requiredEnding)
    {
        super("The File required '" + requiredEnding + "' as ending. Requested: '" + requestedEnding + "'");
    }
    public FileEndingException(String requiredEnding)
    {
        super("The File required '" + requiredEnding + "' as ending.");
    }
}
