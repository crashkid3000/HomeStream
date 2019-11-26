package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.FilmEntitiy;
import com.homestream.HomeStream.vo.ArtistVO;

import java.time.LocalDate;
import java.util.List;

public interface IFilmDAO {
    FilmEntitiy create(FilmEntitiy FilmIdless);
    void delete(FilmEntitiy toBeDeleted);
    void delete(long id);
    List<FilmEntitiy> getAll();
    FilmEntitiy getById(long id);
    List<FilmEntitiy> getByName(String name);
    List<FilmEntitiy> getByReleaseDate(LocalDate release);
    List<FilmEntitiy> getByActor(ArtistVO actor);
    List<FilmEntitiy> getByTags(List<String> tags);
}
