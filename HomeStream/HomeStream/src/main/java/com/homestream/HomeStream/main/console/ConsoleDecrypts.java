package com.homestream.HomeStream.main.console;

import com.homestream.HomeStream.main.exception.CommandException;
import com.homestream.HomeStream.main.exception.CommandItemException;

public class ConsoleDecrypts
{
    /**
     * Console Decrypts
     *
     * Value Shortcuts:
     * -- = no change
     * -<value> = remove this from list without override
     * +<value> = add this to list without override
     *
     * Commands:
     * help = print all Commands
     *
     * info = print General Server Information's
     * info user = print User Server Information's
     * info media = print Media Server Information's
     * info music = print Music Media Server Information's
     * info image = print Image Media Server Information's
     * info video = print Video Media Server Information's
     *
     * create user <Name> <Password> = create new User
     * create roll <Name> <permissions>[array] = create new Roll
     * edit user <userID> <Name> <Password> = edit User
     * edit roll <rollID> <Name> <permissions>[array] = edit Roll
     * delete user <userID> = delete User
     * delete roll <rollID> = delete Roll
     *
     * add roll <userID> <rollID> = add Roll to user else add Default Roll to user
     * remove roll <userID> <rollID> = remove Roll to user
     *
     * edit media <mediaID> <name> <mediaFile> <mediaThumbnail> <mediaDescription> <mediaOwner> <mediaAuthor>[array] <mediaTags>[array] <mediaRolls>[array] = edit Media Information
     * remove media <mediaID> = remove Media
     *
     */

    public void decrypt(String command) throws CommandException {
        String[] commandElements = command.split(" ");
        if(commandElements.length <= 1) throw new CommandException("Unknown Command: " + command);

        if(commandElements[0].equalsIgnoreCase("info")) infoCommands(commandElements);
        else throw new CommandItemException(command, commandElements[0]);
    }

    private void infoCommands(String[] items)
    {

    }
}
