package com.homestream.HomeStream.main.assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader
{
    BufferedReader reader;

    public String LoadFileToString(File file) throws IOException
    {
        String out = "";

        reader = new BufferedReader(new FileReader(file));

        String line;
        while((line = reader.readLine()) != null) out += line;

        return out;
    }
    public String[] LoadFileToStringArray(File file) throws IOException
    {
        ArrayList<String> out = new ArrayList<>();

        reader = new BufferedReader(new FileReader(file));

        String line;
        while((line = reader.readLine()) != null) out.add(line);

        String[] outArray = new String[out.size()];

        for(int i = 0; i < out.size(); i++) outArray[i] = out.get(i);

        return outArray;
    }

    public static String[] readDirectory(String file)
    {
        File directory = new File(file);
        if(!directory.exists() || !directory.isDirectory()) return null;

        File[] files = directory.listFiles();
        ArrayList<String> tempOut = new ArrayList<>();
        for(File f : files) if(f.getName().endsWith(".lang")) tempOut.add(f.getName());

        String[] out = new String[tempOut.size()];
        for(int i = 0; i < out.length; i++) out[i] = tempOut.get(i);

        return out;
    }

}
