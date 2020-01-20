package com.homestream.HomeStream.web.controller;

import com.homestream.HomeStream.web.assets.HTML;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @last_update 14.11.19
     */

    private HTML[] html;

    {
        try {

            html = new HTML[] {
                    new HTML(".\\res\\scripts\\defaultStart.script"),
                    new HTML(".\\res\\scripts\\defaultGallery.script"),
                    new HTML(".\\res\\scripts\\defaultMusic.script"),
                    new HTML(".\\res\\scripts\\defaultPlayer.script")};

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Request for Mainpage
    @RequestMapping("/")
    public String getStart()
    { return html[0].get(); }

    // Request for all Pages bei ID
    @RequestMapping("/{id}")
    public String getGallery(@PathVariable("id") String id)
    {
        if(id.equalsIgnoreCase("gallery"))return html[1].get();
        if(id.startsWith("player")) return html[2].get();
        return html[0].get();
    }


}
