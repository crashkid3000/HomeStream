package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.ImageEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ImageDAO implements IImageDAO {
    @Override
    public List<ImageEntity> getByArtist(ArtistVO artist) {
        return null;
    }

    @Override
    public List<ImageEntity> getByReleaseDate(LocalDate release) {
        return null;
    }

    @Override
    public List<ImageEntity> getByTags(List<String> tags) {
        return null;
    }

    @Override
    public ImageEntity create(ImageEntity Idless) {
        return null;
    }

    @Override
    public void delete(ImageEntity toBeDeleted) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<ImageEntity> getAll() {
        return null;
    }

    @Override
    public Optional<ImageEntity> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<ImageEntity> getByName(String name) {
        return null;
    }
}
