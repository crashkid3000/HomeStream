package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.vo.ArtistVO;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ArtistDAOStub implements IDAO<ArtistVO> {

    public ArtistDAOStub(){
        DBStub.setupDB();
    }

    @Override
    public ArtistVO create(ArtistVO Idless) {
        long id = 0;
        boolean duplicate = false;
        ArtistVO retVal;
        for(ArtistVO a: DBStub.artists){
            if(!duplicate){
                id = Math.max(a.getDbid(), id);
                if(Idless.equals(a)){ //If we already have such a VO, we don't need to save it again
                    duplicate = true;
                }
            }
        }
        retVal = new ArtistVO((duplicate ? id : ++id), Idless);
        return retVal;

    }

    @Override
    public void update(ArtistVO updated){
        System.out.println(this.getClass().getName() + "'s update(...) method was called, but it is not implemented...");
    }

    @Override
    public void delete(ArtistVO toBeDeleted) {
        DBStub.artists.remove(toBeDeleted);
    }

    @Override
    public void delete(long id) {
        Optional<ArtistVO> retVal = getById(id);
        if(retVal.isPresent()){
            DBStub.artists.remove(retVal.get());
        }
    }

    @Override
    public List<ArtistVO> getAll() {
        return DBStub.artists;
    }

    @Override
    public Optional<ArtistVO> getById(long id) {
        int index = 0;
        Optional<ArtistVO> retVal;
        while(index < DBStub.artists.size() && id != DBStub.artists.get(index).getDbid()){
            index++;
        }
        if(index < DBStub.artists.size()){
            retVal = Optional.of(DBStub.artists.get(index));
        }
        else {
            retVal = Optional.empty();
        }
        return retVal;
    }

    @Override
    public List<ArtistVO> getByName(String name) {
        List<ArtistVO> retVal = new LinkedList<>();
        for(ArtistVO a: DBStub.artists){
            if(a.getName().contains(name)){
                retVal.add(a);
            }
        }
        return retVal;
    }
}
