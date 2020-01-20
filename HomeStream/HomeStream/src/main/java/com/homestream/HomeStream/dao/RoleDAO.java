package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.RoleEntity;
import com.homestream.HomeStream.main.exception.IdNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RoleDAO implements IDAO<RoleEntity> {

    private Session currentSession;
    private Transaction currentTransaction;

    /**
     * Gets a SessionFactory
     *
     * @return a SessionFactory
     */
    private static SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder ssrBuilder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        SessionFactory sFactory = cfg.buildSessionFactory(ssrBuilder.build());
        return sFactory;
    }

    /**
     * Opens a new Session
     *
     * @return The newly opened Session
     */
    protected Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    /**
     * Opens a new Session and prepares a transaction, ready for use
     *
     * @return The newly opened Session
     */
    protected Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    /**
     * Closes the current session
     */
    protected void closeCurrentSession() {
        currentSession.close();
    }

    /**
     * Commits the transaction and then closes the session
     */
    protected void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public RoleDAO(){

    }

    @Override
    public RoleEntity create(RoleEntity Idless) {
        long id;
        openCurrentSessionWithTransaction();
        id = (long) currentSession.save(Idless);
        closeCurrentSessionWithTransaction();
        return new RoleEntity(id, Idless);
    }

    @Override
    public void update(RoleEntity updated) throws IdNotFoundException {
        openCurrentSessionWithTransaction();
        currentSession.update(updated);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(RoleEntity toBeDeleted) {
        openCurrentSessionWithTransaction();
        currentSession.delete(toBeDeleted);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(long id) {
        Optional<RoleEntity> toBeDeleted = getById(id);
        if (toBeDeleted.isPresent()) {
            this.delete(toBeDeleted.get());
        }
    }

    @Override
    public List<RoleEntity> getAll() {
        List<RoleEntity> retVal = new LinkedList<>();
        openCurrentSession();
        retVal = (List<RoleEntity>) currentSession.createQuery("from Role").getResultList();
        return retVal;

    }

    @Override
    public Optional<RoleEntity> getById(long id) {
        Optional<RoleEntity> retVal;
        RoleEntity r;
        openCurrentSession();
        r = currentSession.get(RoleEntity.class, id);
        if (r == null) {
            retVal = Optional.empty();
        } else {
            retVal = Optional.of(r);
        }
        return retVal;
    }

    @Override
    public List<RoleEntity> getByName(String name) {
        List<RoleEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<RoleEntity> criteria = builder.createQuery(RoleEntity.class);
        Root<RoleEntity> roleEntityRoot = criteria.from(RoleEntity.class);
        Predicate pred = builder.like(roleEntityRoot.get("name"), name + "*"); //if the artists name starts with the given name
        criteria.select(roleEntityRoot).where(pred);
        TypedQuery<RoleEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;

    }
}
