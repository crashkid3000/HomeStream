package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.vo.UserVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = RoleEntity.class, idClass = Long.class)
public interface RoleDAO extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> getById(long id);

    List<RoleEntity> getByName(String name);

    List<RoleEntity> findAll();

    List<RoleEntity> getByUsers(UserVO user);
}
