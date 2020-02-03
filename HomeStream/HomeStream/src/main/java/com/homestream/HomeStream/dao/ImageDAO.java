package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.dao.stub.IImageDAO;
import com.homestream.HomeStream.entity.ImageEntity;
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

@RepositoryDefinition(domainClass = ImageEntity.class, idClass = Long.class)
public interface ImageDAO extends CrudRepository<ImageEntity, Long> {

    Optional<ImageEntity> getById(long id);

    List<ImageEntity> getByName(String name);

    List<ImageEntity> findAll();

    List<ImageEntity> getByReleaseDate(Date date);

    List<ImageEntity> getByTags(String tags);

    List<ImageEntity> getByUploadedOn(Date uploadedOn);

    List<ImageEntity> getByLastStreamed(Date lastStreamed);

    List<ImageEntity> getByArtists(ArtistVO artist);
}
