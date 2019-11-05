package com.homestream.HomeStream.web.controller;

import com.homestream.HomeStream.web.assets.CSS;
import com.homestream.HomeStream.web.assets.HTML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class Controller
{
    /**
     * Controller to interact with HTTP
     *
     * @author S. Schulze
     * @last_update 2.11.19
     */

    HTML[] site = new HTML[4];
    CSS[] style = new CSS[5];

    {
        try {
            site[0] = new HTML(new File(".\\res\\other\\index.html"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String getHTML()
    {
        return site[0].get();
    }

}
