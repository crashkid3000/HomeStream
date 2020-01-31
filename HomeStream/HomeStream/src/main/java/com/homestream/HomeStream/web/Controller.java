package com.homestream.HomeStream.web;

import com.homestream.HomeStream.main.RequestHandler;
import com.homestream.HomeStream.web.assets.HTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Controller
{
    /**
     * Controller to interact with HTTP
     */

    @Autowired
    RequestHandler requesthandler;
    HttpServletResponse response;

    private HTML[] html;

    /**
     * Load '.script' HTML files;
     */
    {
        try {

            html = new HTML[] {
                    new HTML(".\\res\\scripts\\defaultStart.script"),
                    new HTML(".\\res\\scripts\\defaultGallery.script"),
                    new HTML(".\\res\\scripts\\defaultUpload.script")};

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
        html[1].prepare(requesthandler.search("all"), "home");
        return html[1].get();
    }

    /**
     * Search Request by Search Query
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public String getGallery(@PathVariable("id") String id)
    {
        html[1].prepare(requesthandler.search(id), id);
        return html[1].get();
    }

    /**
     * User Login POST request
     * @param name
     * @param psw
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestParam String name, @RequestParam String psw)
    {
        System.out.println(name + " " + psw);

        //response.addCookie(new Cookie(name, userID));

        return name + " " + psw;
    }

    /**
     * User Register POST request
     * @param name
     * @param psw
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestParam String name, @RequestParam String psw)
    {
        System.out.println(name + " " + psw);

        return name + " " + psw;
    }

    /**
     * Playlist POST request
     * Get Playlist by Playlist ID
     * @param playlistID
     * @return
     */
    @RequestMapping(value = "/playlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String playlist(@RequestParam String playlistID)
    {
        System.out.println(playlistID);

        return playlistID;
    }

    /**
     * Content GET Request
     * Get next Content by Search Query & Content Index
     * @param query
     * @param contentIndex
     * @return
     */
    @RequestMapping(value = "/content", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String content(@RequestParam String query, @RequestParam int contentIndex)
    {
        System.out.println(query + " " + contentIndex);

        return query + " " + contentIndex;
    }


}
