package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.dao.IMusicDAO;
import com.homestream.HomeStream.entity.MusicEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MusicDAOStub implements IMusicDAO {

    public MusicDAOStub(){
        DBStub.setupDB();
    }

    @Override
    public List<MusicEntity> getByArtist(ArtistVO musician) {
        List<MusicEntity> retVal = new LinkedList<>();
        for(MusicEntity me: DBStub.musicEntities){
            if(me.getArtists().contains(musician)){
                retVal.add(me);
            }
        }
        return retVal;
    }

    @Override
    public List<MusicEntity> getByReleaseDate(LocalDate release) {
        List<MusicEntity> retVal = new LinkedList<>();
        for(MusicEntity me: DBStub.musicEntities){
            if (me.getReleaseDate().equals(release)){
                retVal.add(me);
            }
        }
        return retVal;
    }

    @Override
    public List<MusicEntity> getByTags(List<String> tags) {
        List<MusicEntity> retVal = new LinkedList<>();
        boolean searchResult = true; //Have we found all the tags in the respective FilmEntity?
        for(MusicEntity me: DBStub.musicEntities)
        {
            for(String tag: tags){
                if(searchResult && !(me.getTags().contains(tag))){
                    searchResult = false;
                }
            }
            if(searchResult){
                retVal.add(me);
            }
            searchResult = true;
        }
        return retVal;
    }

    @Override
    public MusicEntity create(MusicEntity Idless) {
        long id = 0;
        MusicEntity retVal;
        for(MusicEntity me: DBStub.musicEntities){
            id = Math.max(me.getId(), id);
        }
        id++;
        retVal = new MusicEntity(id, Idless);
        DBStub.musicEntities.add(retVal);
        return retVal;
    }

    @Override
    public void update(MusicEntity updated){
        System.out.println(this.getClass().getName() + "'s update(...) method was called, but it is not implemented...");
    }

    @Override
    public void delete(MusicEntity toBeDeleted) {
        DBStub.musicEntities.remove(toBeDeleted);
    }

    @Override
    public void delete(long id) {
        Optional<MusicEntity> me = getById(id);
        if(me.isPresent()){
            DBStub.musicEntities.remove(me.get());
        }
    }

    @Override
    public List<MusicEntity> getAll() {
        return DBStub.musicEntities;
    }

    @Override
    public Optional<MusicEntity> getById(long id) {
        int index = 0;
        Optional<MusicEntity> retVal;
        while(index < DBStub.musicEntities.size() && id != DBStub.musicEntities.get(index).getId()){
            index++;
        }
        if(index < DBStub.musicEntities.size()){
            retVal = Optional.of(DBStub.musicEntities.get(index));
        }
        else {
            retVal = Optional.empty();
        }
        return retVal;
    }

    @Override
    public List<MusicEntity> getByName(String name) {
        List<MusicEntity> retVal = new LinkedList<>();
        for(MusicEntity me: DBStub.musicEntities){
            if(me.getName().contains(name)){
                retVal.add(me);
            }
        }
        return retVal;
    }
}
