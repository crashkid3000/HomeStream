package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.vo.ArtistVO;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ArtistDAO implements IDAO<ArtistVO> {
    @Override
    public ArtistVO create(ArtistVO Idless) {
        return null;
    }

    @Override
    public void delete(ArtistVO toBeDeleted) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<ArtistVO> getAll() {
        return null;
    }

    @Override
    public Optional<ArtistVO> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<ArtistVO> getByName(String name) {
        return null;
    }
}
