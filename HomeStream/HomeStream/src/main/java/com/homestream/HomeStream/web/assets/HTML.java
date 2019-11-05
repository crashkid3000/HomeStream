package com.homestream.HomeStream.web.assets;

import com.homestream.HomeStream.main.exception.FileEndingException;

import java.io.*;

public class HTML
{
    File file;

    String data;

    public HTML(File htmlFile) throws IOException {
        String name = htmlFile.getName().toLowerCase();
        if(name.endsWith(".html")) file = htmlFile;
        else throw new FileEndingException(name.split(".")[1], "html");

        data = "";
        read();
    }

    private void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) data += line;

        reader.close();
    }

    public String get(){ return data; }
}
