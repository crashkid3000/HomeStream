package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.entity.FilmEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FilmDAOStub implements IFilmDAO {

    public FilmDAOStub(){
        DBStub.setupDB();
    }

    @Override
    public List<FilmEntity> getByActor(ArtistVO actor) {
        List<FilmEntity> retVal = new LinkedList<>();
        for(FilmEntity fe: DBStub.films){
            if(fe.getMainActors().contains(actor)){
                retVal.add(fe);
            }
        }
        return retVal;
    }

    @Override
    public List<FilmEntity> getByReleaseDate(LocalDate release) {
        List<FilmEntity> retVal = new LinkedList<>();
        for(FilmEntity fe: DBStub.films){
            if (fe.getReleaseDate().equals(release)){
                retVal.add(fe);
            }
        }
        return retVal;
    }

    @Override
    public List<FilmEntity> getByTags(List<String> tags) {
        List<FilmEntity> retVal = new LinkedList<>();
        boolean searchResult = true; //Have we found all the tags in the respective FilmEntity?
        for(FilmEntity fe: DBStub.films)
        {
            for(String tag: tags){
                if(searchResult && !(fe.getTags().contains(tag))){
                    searchResult = false;
                }
            }
            if(searchResult){
                retVal.add(fe);
            }
            searchResult = true;
        }
        return retVal;
    }

    @Override
    public FilmEntity create(FilmEntity Idless) {
        long id = 0;
        FilmEntity retVal;
        for(FilmEntity fe: DBStub.films){
            id = Math.max(fe.getId(), fe.getId());
        }
        retVal = new FilmEntity(id, Idless);
        DBStub.films.add(retVal);
        return retVal;
    }

    @Override
    public void update(FilmEntity updated){
        System.out.println(this.getClass().getName() + "'s update(...) method was called, but it is not implemented...");
    }

    @Override
    public void delete(FilmEntity toBeDeleted) {
        DBStub.films.remove(toBeDeleted);
    }

    @Override
    public void delete(long id) {
        Optional<FilmEntity> toBeDeleted = getById(id);
        if(toBeDeleted.isPresent()){
            DBStub.films.remove(toBeDeleted.get());
        }
    }

    @Override
    public List<FilmEntity> getAll() {
        return DBStub.films;
    }

    @Override
    public Optional<FilmEntity> getById(long id) {
        int index = 0;
        Optional<FilmEntity> retVal;
        while(index < DBStub.films.size() && id != DBStub.films.get(index).getId()){
            index++;
        }
        if(index < DBStub.films.size()){ //if we actually found an object with matching id
            retVal = Optional.of(DBStub.films.get(index));
        }
        else {
            retVal = Optional.empty();
        }
        return retVal;
    }

    @Override
    public List<FilmEntity> getByName(String name) {
        List<FilmEntity> retVal = new LinkedList<>();
        for(FilmEntity fe: DBStub.films){
            if(fe.getName().contains(name)){
                retVal.add(fe);
            }
        }
        return retVal;
    }
}
