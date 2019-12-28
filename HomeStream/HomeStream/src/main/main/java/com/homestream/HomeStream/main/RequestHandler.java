<<<<<<<HEAD
package com.homestream.HomeStream.main;

import com.homestream.HomeStream.dao.stub.FilmDAOStub;
import com.homestream.HomeStream.dao.stub.ImageDAOStub;
import com.homestream.HomeStream.dao.stub.MusicDAOStub;
import com.homestream.HomeStream.entity.MediaEntity;
import com.homestream.HomeStream.entity.MusicEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RequestHandler {

    MusicDAOStub music = new MusicDAOStub();
    FilmDAOStub film = new FilmDAOStub();
    ImageDAOStub image = new ImageDAOStub();

    public List<? extends MediaEntity> search(String SearchInput) {

        switch (SearchInput) {

        case "music":
            return music.getAll();
        case "film":
            return film.getAll();
        case "image":
            return image.getAll();
        default:
            return getFromAll(SearchInput);
        }
    }

    public Optional<MusicEntity> searchByID(String SearchInput) {

        return music.getById(Long.parseLong(SearchInput.replaceFirst("player_", "")));
    }

    private List<MediaEntity> getFromAll(String SearchInput) {
        List<MediaEntity> returnList = new LinkedList<>();

        returnList.addAll(music.getByName(SearchInput));
        returnList.addAll(film.getByName(SearchInput));
        returnList.addAll(image.getByName(SearchInput));

        return returnList;
    }

    private String formatSearInput(String SearchInput) {
        return SearchInput.toLowerCase();
    }

    public void saveFile(final File file, final String Filename) {
        if (!file.exists()) {
            try {
                File directory = new File(res / data);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                log("Excepton Occured: " + e.toString());
            }
            return;
        }
        log("file already exists");

    }
