package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.dao.stub.IDAO;
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

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = ArtistVO.class, idClass = Long.class)
public interface ArtistDAO extends CrudRepository<ArtistVO, Long> {

    Optional<ArtistVO> getByDbid(long id);

    List<ArtistVO> getByName(String name);

    List<ArtistVO> findAll();

}
