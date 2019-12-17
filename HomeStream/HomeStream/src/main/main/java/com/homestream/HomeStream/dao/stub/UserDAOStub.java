package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.dao.IDAO;
import com.homestream.HomeStream.vo.UserVO;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDAOStub implements IDAO<UserVO> {

    public UserDAOStub(){
        DBStub.setupDB();
    }

    @Override
    public UserVO create(UserVO Idless) {
        long id = 0;
        boolean duplicate = false;
        UserVO retVal;
        for(UserVO u: DBStub.users){
            if(!duplicate){
                id = Math.max(u.getDbid(), id);
                if(Idless.equals(u)){ //If we already have such a VO, we don't need to save it again
                    duplicate = true;
                }
            }
        }
        retVal = new UserVO((duplicate ? id : ++id), Idless);
        return retVal;
    }

    @Override
    public void delete(UserVO toBeDeleted) {
        DBStub.users.remove(toBeDeleted);
    }

    @Override
    public void delete(long id) {
        Optional<UserVO> retVal = getById(id);
        if(retVal.isPresent()){
            DBStub.users.remove(retVal.get());
        }
    }

    @Override
    public List<UserVO> getAll() {
        return DBStub.users;
    }

    @Override
    public Optional<UserVO> getById(long id) {
        int index = 0;
        Optional<UserVO> retVal;
        while(index < DBStub.users.size() && id != DBStub.users.get(index).getDbid()){
            index++;
        }
        if(index < DBStub.users.size()){
            retVal = Optional.of(DBStub.users.get(index));
        }
        else {
            retVal = Optional.empty();
        }
        return retVal;
    }

    @Override
    public List<UserVO> getByName(String name) {
        List<UserVO> retVal = new LinkedList<>();
        for(UserVO u: DBStub.users){
            if(u.getName().contains(name)){
                retVal.add(u);
            }
        }
        return retVal;
    }
}
