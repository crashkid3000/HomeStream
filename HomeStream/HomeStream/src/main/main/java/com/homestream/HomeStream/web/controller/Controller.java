package com.homestream.HomeStream.web.controller;

import com.homestream.HomeStream.entity.MediaEntity;
import com.homestream.HomeStream.main.RequestHandler;
import com.homestream.HomeStream.web.assets.HTML;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller
{
    /**
     * Controller to interact with HTTP
     *
     * @author S. Schulze
     * @last_update 14.11.19
     */

    RequestHandler requesthandler = new RequestHandler();

    private HTML[] html;

    {
        try {

            html = new HTML[] {
                    new HTML(".\\res\\scripts\\defaultStart.script", true, false),
                    new HTML(".\\res\\scripts\\defaultGallery.script", true, false),
                    new HTML(".\\res\\scripts\\defaultMusic.script", false, true),
                    new HTML(".\\res\\scripts\\defaultPlayer.script", false, false)};

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
        System.out.println(id);

        if(id.equalsIgnoreCase("music") || id.equalsIgnoreCase("film"))
        {
            System.out.println(requesthandler.search(id).size());
            html[1].prepare((List<MediaEntity>) requesthandler.search(id), id);
            return html[1].get();
        }
        else if(id.startsWith("player")) return html[2].get(requesthandler.searchByID(id).get());
        else
        {
            System.out.println(requesthandler.search(id).size());
            html[1].prepare((List<MediaEntity>) requesthandler.search(id), id);
            return html[1].get();
        }
    }


}
