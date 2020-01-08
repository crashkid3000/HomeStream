package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.dao.IImageDAO;
import com.homestream.HomeStream.dao.ImageDAO;
import com.homestream.HomeStream.entity.ImageEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ImageDAOStub implements IImageDAO {

    public ImageDAOStub(){
        DBStub.setupDB();
    }

    @Override
    public List<ImageEntity> getByArtist(ArtistVO artist) {
        List<ImageEntity> retVal = new LinkedList<>();
        for(ImageEntity ie: DBStub.images){
            if(ie.getArtists().contains(artist)){
                retVal.add(ie);
            }
        }
        return retVal;
    }

    @Override
    public List<ImageEntity> getByReleaseDate(LocalDate release) {
        List<ImageEntity> retVal = new LinkedList<>();
        for(ImageEntity ie: DBStub.images){
            if (ie.getReleaseDate().equals(release)){
                retVal.add(ie);
            }
        }
        return retVal;
    }

    @Override
    public List<ImageEntity> getByTags(List<String> tags) {
        List<ImageEntity> retVal = new LinkedList<>();
        boolean searchResult = true; //Have we found all the tags in the respective FilmEntity?
        for(ImageEntity ie: DBStub.images)
        {
            for(String tag: tags){
                if(searchResult && !(ie.getTags().contains(tag))){
                    searchResult = false;
                }
            }
            if(searchResult){
                retVal.add(ie);
            }
            searchResult = true;
        }
        return retVal;
    }

    @Override
    public ImageEntity create(ImageEntity Idless) {
        long id = 0;
        ImageEntity retVal;
        for(ImageEntity ie: DBStub.images){
            id = Math.max(ie.getId(), id);
        }
        id++;
        retVal = new ImageEntity(id, Idless);
        DBStub.images.add(retVal);
        return retVal;
    }

    @Override
    public void update(ImageEntity updated){
        System.out.println(this.getClass().getName() + "'s update(...) method was called, but it is not implemented...");
    }

    @Override
    public void delete(ImageEntity toBeDeleted) {
        DBStub.images.remove(toBeDeleted);
    }

    @Override
    public void delete(long id) {
        Optional<ImageEntity> retVal = getById(id);
        if(retVal.isPresent()){
            DBStub.images.remove(retVal.get());
        }
    }

    @Override
    public List<ImageEntity> getAll() {
        return DBStub.images;
    }

    @Override
    public Optional<ImageEntity> getById(long id) {
        int index = 0;
        Optional<ImageEntity> retVal;
        while(index < DBStub.images.size() && id != DBStub.images.get(index).getId()){
            index++;
        }
        if(index < DBStub.images.size()){
            retVal = Optional.of(DBStub.images.get(index));
        }
        else {
            retVal = Optional.empty();
        }
        return retVal;
    }

    @Override
    public List<ImageEntity> getByName(String name) {
        List<ImageEntity> retVal = new LinkedList<>();
        for(ImageEntity ie: DBStub.images){
            if(ie.getName().contains(name)){
                retVal.add(ie);
            }
        }
        return retVal;
    }
}
