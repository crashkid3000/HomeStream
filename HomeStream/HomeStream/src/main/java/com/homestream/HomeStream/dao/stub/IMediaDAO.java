package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.dao.stub.IDAO;
import com.homestream.HomeStream.entity.MediaEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * A generic DAO that additionally requires other <code>get</code> methods catering to media-oriented objects
 * @author Justin Braack
 * @param <T> A subclass of MediaType, representing a specific type of media
 */
public interface IMediaDAO<T extends MediaEntity> extends IDAO<T> {
    /**
     * Gets all media objects that have the given date as their rlease date/date of creation
     * @param release The release date
     * @return All media entities with said release date
     */
    List<T> getByReleaseDate(LocalDate release);

    /**
     * Gets all media objects that fulfill <b>all</b> of the given tags
     * @param tags The list of tags to look out for
     * @return Every media object that has <b>all</b> of the given tags inside
     */
    List<T> getByTags(List<String> tags);
}
