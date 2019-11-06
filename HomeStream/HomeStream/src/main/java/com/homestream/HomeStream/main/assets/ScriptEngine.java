package com.homestream.HomeStream.main.assets;

import com.homestream.HomeStream.main.exception.LineFormatMismatch;
import com.homestream.HomeStream.main.exception.ScriptFormatException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScriptEngine
{
    /**
     * Class to to read '.script' Files
     *
     * @author S. Schulze
     * @last_update 2.11.19
     */

    File script;

    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> keys = new ArrayList<>();
    Map<String, String[]> line = new HashMap<>();

    // Main Methode to read '.script' Files
    public void setScript(File scriptFile) throws IOException
    {
        script = scriptFile;

        read();
        decrypt();
    }

    // Read File line by Line
    private void read() throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(script));

        String line;
        while ((line = reader.readLine()) != null && line.replace(" ", "").length() > 0) data.add(line);

        reader.close();
    }

    // Decode File Data
    private void decrypt() throws IOException
    {
        for (String item : data)
        {
            String element[] = item.split(" ");
            String key;
            String[] parameter = new String[element.length - 1];

            if(!element[0].endsWith(":")) throw new ScriptFormatException(element[0]);
            else key = element[0];

            keys.add(key);

            if(element.length < 2) throw new LineFormatMismatch();
            for( int i = 1; i < element.length; i++)
            {
                String param[] = element[i].split("=");
                if(param.length != 2) throw new LineFormatMismatch();
                if(!param[1].startsWith("'") || !param[1].endsWith("'")) throw new ScriptFormatException(param[1]);

                parameter[i - 1] = element[i];
            };

            line.put(key, parameter);

        }
    }

    public String[] getLine(String key){ return line.get(key); }

    public String[] getKeys()
    {
        String[] out = new String[keys.size()];
        for (int i = 0; i < keys.size(); i++) out[i] =  keys.get(i);

        return out;
    }
}
