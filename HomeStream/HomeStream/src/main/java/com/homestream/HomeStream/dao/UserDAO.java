package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.main.exception.IdNotFoundException;
import com.homestream.HomeStream.vo.UserVO;

import java.util.List;
import java.util.Optional;

public class UserDAO implements IDAO<UserVO> {

    @Override
    public UserVO create(UserVO Idless) {
        return null;
    }

    @Override
    public void update(UserVO updated) throws IdNotFoundException {

    }

    @Override
    public void delete(UserVO toBeDeleted) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<UserVO> getAll() {
        return null;
    }

    @Override
    public Optional<UserVO> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<UserVO> getByName(String name) {
        return null;
    }
}
