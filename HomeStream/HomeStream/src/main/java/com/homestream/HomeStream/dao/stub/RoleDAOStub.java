package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.dao.IDAO;
import com.homestream.HomeStream.entity.RoleEntity;

import java.util.*;

public class RoleDAOStub implements IDAO<RoleEntity> {


    public RoleDAOStub(){
        DBStub.setupDB();
    }

    @Override
    public RoleEntity create(RoleEntity roleEntitiy) {
        long id = 0;
        for(RoleEntity re: DBStub.roles){
            id = Math.max(roleEntitiy.getId(), id); //Find highest ID in roles...
        }
        id++; //...add one to that to create new ID
        RoleEntity e = new RoleEntity(id, roleEntitiy);
        DBStub.roles.add(e);
        return e;

    }

    @Override
    public void update(RoleEntity updated){
        System.out.println(this.getClass().getName() + "'s update(...) method was called, but it is not implemented...");
    }

    @Override
    public void delete(RoleEntity toBeDeleted) {
        DBStub.roles.remove(toBeDeleted);
    }

    @Override
    public void delete(long id) {
        Optional<RoleEntity> e = getById(id);
        if (e.isPresent()){
            DBStub.roles.remove(e.get());
        }
    }

    @Override
    public List<RoleEntity> getAll() {
        return DBStub.roles;
    }

    @Override
    public Optional<RoleEntity> getById(long id) {
        Optional<RoleEntity> retVal;
        int index = 0;
        while(index < DBStub.roles.size() && DBStub.roles.get(index).getId() != id){
            //index < DBStub.roles.size(): Cancellation statement; i.e. go out of the loop if we reached the end of the list
            //DBStub.roles.get(index).getId() != id What we're actually looking for: The index of the element whose ID matches the given ID
            index++;
        }
        if(index < DBStub.roles.size()){
            //We only get here if the second condition of the WHILE loop is true
            retVal =  Optional.of(DBStub.roles.get(index));
        }
        else {
            retVal = Optional.empty();
        }
        return retVal;
    }

    @Override
    public List<RoleEntity> getByName(String name) {
        List<RoleEntity> retVal = new LinkedList<>();
        for(RoleEntity re: DBStub.roles)
            if(re.getName().contains(name)){
                retVal.add(re);
            }
        return retVal;
    }
}
