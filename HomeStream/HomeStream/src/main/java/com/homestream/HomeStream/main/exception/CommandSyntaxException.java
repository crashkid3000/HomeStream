package com.homestream.HomeStream.main.exception;

public class CommandSyntaxException extends CommandException
{
    public CommandSyntaxException(String command, String item)
    {
        super("Unknown Command Element: " + command.replaceFirst(item,"'" + item + "'"));
    }
}
