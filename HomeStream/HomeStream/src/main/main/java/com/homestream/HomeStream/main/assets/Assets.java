package com.homestream.HomeStream.main.assets;

public class Assets
{
    public static boolean checkFileType(String fileName, String type, String... types)
    {
        if(fileName.endsWith(type.replaceFirst(".",""))) return true;
        for (String s : types) if(fileName.endsWith(s.replaceFirst(".",""))) return true;
        return false;
    }
}
