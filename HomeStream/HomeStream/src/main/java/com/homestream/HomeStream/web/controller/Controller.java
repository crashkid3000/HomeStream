package com.homestream.HomeStream.web.controller;

import com.homestream.HomeStream.web.assets.HTML;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller
{
    /**
     * Controller to interact with HTTP
     *
     * @author S. Schulze
     * @last_update 7.11.19
     */

    private HTML[] site = null;

    {
        try {

            site = new HTML[]
                    {new HTML(".\\res\\scripts\\defaultStart.script")};

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String getStart()
    { return site[0].get(); }

    @RequestMapping("/gallery")
    public String getGallery() { return site[0].get(); }

    @RequestMapping("/gallery/{id}")
    public String getGallery(@PathVariable("id") String id) { return site[0].get(); }

    @RequestMapping("/stream/{id}")
    public String getPlayer(@PathVariable("id") String id)
    { return site[0].get(); }

    @RequestMapping("/settings")
    public String getConfig()
    { return site[0].get(); }

}
