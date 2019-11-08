package com.homestream.HomeStream.web.assets;

import com.homestream.HomeStream.web.generator.WebGenerator;

import java.io.*;

public class HTML
{
    /**
     * Class to store HTML
     *
     * @author S. Schulze
     * @last_update 5.11.19
     */

    String data;
    WebGenerator generator = new WebGenerator();

    public HTML(String scriptFile) throws IOException { data = generator.generate(scriptFile); }

    public String get(){ return data; }
}
