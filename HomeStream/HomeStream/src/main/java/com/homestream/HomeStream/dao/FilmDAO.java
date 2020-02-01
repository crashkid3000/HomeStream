package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.dao.stub.IFilmDAO;
import com.homestream.HomeStream.entity.FilmEntity;
import com.homestream.HomeStream.main.exception.IdNotFoundException;
import com.homestream.HomeStream.vo.ArtistVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = FilmEntity.class, idClass = Long.class)
public interface FilmDAO extends CrudRepository<FilmEntity, Long> {

    Optional<FilmEntity> getById(long id);

    List<FilmEntity> getByName(String name);

    List<FilmEntity> findAll();

    List<FilmEntity> getByReleaseDate(Date date);

    List<FilmEntity> getByTags(String tags);

    List<FilmEntity> getByUploadedOn(Date uploadedOn);

    List<FilmEntity> getByLastStreamed(Date lastStreamed);

    List<FilmEntity> getByMainActors(ArtistVO artist);
}
