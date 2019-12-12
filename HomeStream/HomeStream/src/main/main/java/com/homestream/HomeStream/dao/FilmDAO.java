package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.FilmEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FilmDAO implements IFilmDAO {
    @Override
    public List<FilmEntity> getByActor(ArtistVO actor) {
        return null;
    }

    @Override
    public List<FilmEntity> getByReleaseDate(LocalDate release) {
        return null;
    }

    @Override
    public List<FilmEntity> getByTags(List<String> tags) {
        return null;
    }

    @Override
    public FilmEntity create(FilmEntity Idless) {
        return null;
    }

    @Override
    public void delete(FilmEntity toBeDeleted) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<FilmEntity> getAll() {
        return null;
    }

    @Override
    public Optional<FilmEntity> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<FilmEntity> getByName(String name) {
        return null;
    }
}
