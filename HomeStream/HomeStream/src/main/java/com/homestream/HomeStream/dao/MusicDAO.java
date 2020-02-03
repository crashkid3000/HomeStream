package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.MusicEntity;
import com.homestream.HomeStream.main.exception.IdNotFoundException;
import com.homestream.HomeStream.vo.ArtistVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@RepositoryDefinition(domainClass = MusicEntity.class, idClass = Long.class)
@Transactional(readOnly = true)
public interface MusicDAO extends CrudRepository<MusicEntity, Long> {

    /**
     * Get the object with a specific <code>id</code>
     * @param id The ID of the object that shall be returned
     * @return The object with that <code>id</code>, hidden inside the Optional that is returned. If none if found, Optional.empty() is returned
     */
    Optional<MusicEntity> getById(long id);

    List<MusicEntity> getByName(String name);

    List<MusicEntity> findAll();

    List<MusicEntity> getByReleaseDate(Date date);

    List<MusicEntity> getByTags(String tags);

    List<MusicEntity> getByUploadedOn(Date uploadedOn);

    List<MusicEntity> getByLastStreamed(Date lastStreamed);

    List<MusicEntity> getByArtists(ArtistVO artist);

}