package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public class RoleDAO implements IDAO<RoleEntity> {
    @Override
    public RoleEntity create(RoleEntity roleEntitiy) {
        return null;
    }

    @Override
    public void delete(RoleEntity toBeDeleted) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<RoleEntity> getAll() {
        return null;
    }

    @Override
    public Optional<RoleEntity> getById(long id) {
        return null;
    }

    @Override
    public List<RoleEntity> getByName(String name) {
        return null;
    }
}
