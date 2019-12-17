package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.MusicEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MusicDAO implements IMusicDAO {
    @Override
    public List<MusicEntity> getByArtist(ArtistVO musician) {
        return null;
    }

    @Override
    public List<MusicEntity> getByReleaseDate(LocalDate release) {
        return null;
    }

    @Override
    public List<MusicEntity> getByTags(List<String> tags) {
        return null;
    }

    @Override
    public MusicEntity create(MusicEntity Idless) {
        return null;
    }

    @Override
    public void delete(MusicEntity toBeDeleted) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<MusicEntity> getAll() {
        return null;
    }

    @Override
    public Optional<MusicEntity> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<MusicEntity> getByName(String name) {
        return null;
    }
}
