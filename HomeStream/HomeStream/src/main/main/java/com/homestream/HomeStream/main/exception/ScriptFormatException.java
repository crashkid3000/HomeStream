package com.homestream.HomeStream.main.exception;

import java.io.IOException;

public class ScriptFormatException extends IOException {

    public ScriptFormatException(String item)
    {
        super("Unknown Script Element '" + item + "'");
    }
}
