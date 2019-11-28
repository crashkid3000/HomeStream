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
     * @last_update 14.11.19
     */

    private HTML[] html;

    {
        try {

            html = new HTML[] {
                    new HTML(".\\res\\scripts\\defaultStart.script"),
                    new HTML(".\\res\\scripts\\defaultGallery.script")};

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
        return html[0].get();
    }

    // Request for Content return
    @RequestMapping("/content/{content}/{resolution}")
    public String getContent(@PathVariable("content") String id, @PathVariable("resolution") byte resolution)
    {
        if(id.equalsIgnoreCase("LAST_UPLOAD"));
        else if(id.equalsIgnoreCase("LAST_STREAM"));

        System.out.println(resolution);

        return "" + resolution;
    }

    // Request Stream
    @RequestMapping("/stream/{content}")
    public String getStream(@PathVariable("content") String id)
    {
        return null;
    }


}
