package com.homestream.HomeStream.web.controller;

import com.homestream.HomeStream.entity.MediaEntity;
import com.homestream.HomeStream.main.RequestHandler;
import com.homestream.HomeStream.web.assets.HTML;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
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

    String lastSearch = "";

    RequestHandler requesthandler = new RequestHandler();

    private HTML[] html;

    /**
     * Load '.script' HTML files;
     */
    {
        try {

            html = new HTML[] {
                    new HTML(".\\res\\scripts\\defaultStart.script", true, false, false),
                    new HTML(".\\res\\scripts\\defaultGallery.script", true, false, false),
                    new HTML(".\\res\\scripts\\defaultMusic.script", false, true, false),
                    new HTML(".\\res\\scripts\\defaultPlayer.script", false, false, true),
                    new HTML(".\\res\\scripts\\defaultUpload.script", true, false, false)};

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Load Main Page and request All content
     * @return
     */
    @RequestMapping("/")
    public String getStart()
    {
        html[1].prepare(requesthandler.search("all"), "home", false);
        return html[1].get();
    }

    /**
     * Search Request
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public String getGallery(@PathVariable("id") String id)
    {


        if(id.startsWith("audio_")) return html[2].get(requesthandler.searchByID(id).get(), (List<MediaEntity>) requesthandler.search("music"));
        else if(id.startsWith("video_")) return html[3].get(requesthandler.searchByID(id).get());
        else
        {
            lastSearch = id;

            html[1].prepare((List<MediaEntity>) requesthandler.search(id), id, false);
            return html[1].get();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        modelMap.addAttribute("file", file);

        System.out.println(file.getOriginalFilename());

        requesthandler.save(file);

        return html[4].get();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String login(@RequestParam String name, @RequestParam String psw)
    {
        System.out.println(name + " " + psw);

        return name + " " + psw;
    }


}
