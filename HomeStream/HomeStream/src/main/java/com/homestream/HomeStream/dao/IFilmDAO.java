package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.FilmEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.util.List;

public interface IFilmDAO extends IMediaDAO<FilmEntity>{
    List<FilmEntity> getByActor(ArtistVO actor);

}
