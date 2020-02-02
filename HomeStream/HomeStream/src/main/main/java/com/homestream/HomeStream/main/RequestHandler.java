
package com.homestream.HomeStream.main;

import com.homestream.HomeStream.dao.stub.FilmDAOStub;
import com.homestream.HomeStream.dao.stub.ImageDAOStub;
import com.homestream.HomeStream.dao.stub.MusicDAOStub;
import com.homestream.HomeStream.entity.FilmEntity;
import com.homestream.HomeStream.entity.ImageEntity;
import com.homestream.HomeStream.entity.MediaEntity;
import com.homestream.HomeStream.entity.MusicEntity;
import com.homestream.HomeStream.main.assets.Assets;
import com.homestream.HomeStream.vo.ArtistVO;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RequestHandler {

    MusicDAOStub music = new MusicDAOStub();
    FilmDAOStub video = new FilmDAOStub();
    ImageDAOStub image = new ImageDAOStub();

    ArrayList<? extends MediaEntity> extSearch = new ArrayList<>();

    /**
     * Primary Function to search database by query
     * 
     * @param searchInput
     * @return
     */
    public List<? extends MediaEntity> search(String searchInput) {

        /**
         * catch extended Search query
         */
        if (searchInput.startsWith("ext")) {
            ArrayList<List<MediaEntity>> listArray = new ArrayList<>();
            List<MediaEntity> out = new LinkedList<>();
            String[] searchParam = searchInput.split("§");

            for (int i = 1; i < searchParam.length; i++) {
                switch (searchParam[i].split(",")[0]) {
                case "TITLE":
                    System.out.println("Search by Title: " + searchParam[i].split(",")[1]);
                    listArray.add(searchForParam(searchParam[i].split(",")[1], "name"));
                    break;
                case "PLAYLIST":
                    System.out.println("Search by Playlist Name: " + searchParam[i].split(",")[1]);
                    listArray.add(searchForParam(searchParam[i].split(",")[1], "playlist"));
                    break;
                case "ARTIST":
                    System.out.println("Search by Artist: " + searchParam[i].split(",")[1]);
                    listArray.add(searchForParam(searchParam[i].split(",")[1], "artist"));
                    break;
                case "OWNER":
                    System.out.println("Search by Owner: " + searchParam[i].split(",")[1]);
                    listArray.add(searchForParam(searchParam[i].split(",")[1], "owner"));
                    break;
                case "RELEASE":
                    System.out.println("Search by Release: " + searchParam[i].split(",")[1]);
                    listArray.add(searchForParam(searchParam[i].split(",")[1], "release"));
                    break;
                case "GENRE":
                    System.out.println("Search by Genre: " + searchParam[i].split(",")[1]);
                    listArray.add(searchForParam(searchParam[i].split(",")[1], "genre"));
                    break;
                case "TAGS":
                    ;
                    System.out.println("Search by Tags: " + searchParam[i].split(",")[1]);
                    listArray.add(searchForParam(searchParam[i].split(",")[1], "tags"));
                    break;
                }
            }

            out.addAll(listArray.get(0));
            for (int i = 1; i < listArray.size(); i++) {
                out.retainAll(listArray.get(i));
            }
            return out;
        }

        /**
         * catch default Search query
         */
        switch (searchInput) {

        case "music":
            return music.getAll();
        case "film":
            return video.getAll();
        case "image":
            return image.getAll();
        case "all":
            return getFromAll();

        default:
            return getFromAll(searchInput);
        }
    }

    /**
     * Secondary Function to search database by ID query
     * 
     * @param SearchInput
     * @return
     */
    public Optional<? extends MediaEntity> searchByID(String SearchInput) {

        if (SearchInput.startsWith("audio_"))
            return music.getById(Long.parseLong(SearchInput.replaceFirst("audio_", "")));
        else
            return video.getById(Long.parseLong(SearchInput.replaceFirst("video_", "")));
    }

    /**
     * Internal Function to request all types by query
     * 
     * @param SearchInput
     * @return
     */
    private List<MediaEntity> getFromAll(String SearchInput) {
        List<MediaEntity> returnList = new LinkedList<>();

        returnList.addAll(music.getByName(SearchInput));
        returnList.addAll(video.getByName(SearchInput));
        returnList.addAll(image.getByName(SearchInput));

        return returnList;
    }

    /**
     * Internal Function to request all types without query
     * 
     * @return
     */
    private List<MediaEntity> getFromAll() {
        List<MediaEntity> returnList = new LinkedList<>();

        returnList.addAll(music.getAll());
        returnList.addAll(video.getAll());
        returnList.addAll(image.getAll());

        return returnList;
    }

    private String formatSearInput(String SearchInput) {
        return SearchInput.toLowerCase();
    }

    /**
     * Simple save Function to Upload Data
     * 
     * @param file
     * @return
     */
    public String save(MultipartFile file) {

        try {
            File onServer = new File("./res/data/" + file.getOriginalFilename());

            if (onServer.exists())
                ;

            FileUtils.writeByteArrayToFile(onServer, file.getBytes());

            if (Assets.checkFileType(file.getOriginalFilename(), "mp3"))
                music.create(new MusicEntity(1, file.getOriginalFilename(), LocalDate.of(1999, 1, 1),
                        LocalDateTime.now(), file.getName(), 4395643, null, null, "demolition_racer.png",
                        new LinkedList<String>(), null, LocalTime.of(0, 4, 29)));
            else if (Assets.checkFileType(file.getOriginalFilename(), "png")
                    || Assets.checkFileType(file.getOriginalFilename(), "jpg"))
                image.create(new ImageEntity(0, file.getOriginalFilename(), LocalDate.of(2019, 9, 15),
                        LocalDateTime.now(), "gre_with_chrome_bl.png", 410376, null, null, null,
                        new LinkedList<String>(), null, new int[] { 1000, 400 }));
            else if (Assets.checkFileType(file.getOriginalFilename(), "mp4"))
                video.create(new FilmEntity(1, file.getOriginalFilename(), LocalDate.of(1999, 8, 28),
                        LocalDateTime.now(), file.getOriginalFilename(), 15872449, null, null, null,
                        new LinkedList<String>(), null, null, LocalTime.of(0, 3, 19)));

            return "File saved";
        } catch (IOException ioE) {
            return null;
        }
    }

    /**
     * Internal Function to prepare extended Search by query
     * 
     * @param param
     * @param type
     * @return
     */
    private List<MediaEntity> searchForParam(String param, String type) {
        List tagList = new LinkedList();
        List<MediaEntity> out = new LinkedList<>();

        String[] params = param.split(" ");

        for (String s : params) {
            switch (type) {
            case "name":
                out.addAll(music.getByName(s));
                out.addAll(image.getByName(s));
                out.addAll(video.getByName(s));
                break;
            case "playlist":
                System.out.println("playlist: NOT IMPLEMENTED");
                break;
            case "artist":
                out.addAll(music.getByArtist(new ArtistVO(s, LocalDate.of(1993, 1, 1))));
                out.addAll(image.getByArtist(new ArtistVO(s, LocalDate.of(1993, 1, 1))));
                out.addAll(video.getByActor(new ArtistVO(s, LocalDate.of(1993, 1, 1))));
                break;
            case "owner":
                System.out.println("Owner: NOT IMPLEMENTED");
                break;
            case "release":
                System.out.println("Release: NOT IMPLEMENTED");
                break;
            case "genre":
                System.out.println("Genre: NOT IMPLEMENTED");
                break;
            case "tags":
                tagList.clear();
                tagList.add(s);

                out.addAll(music.getByTags(tagList));
                out.addAll(image.getByTags(tagList));
                out.addAll(video.getByTags(tagList));
                break;
            }
        }

        return out;
    }
}
