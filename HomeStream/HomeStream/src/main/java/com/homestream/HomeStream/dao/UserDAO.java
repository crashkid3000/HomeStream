package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.main.exception.IdNotFoundException;
import com.homestream.HomeStream.vo.UserVO;
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

public class UserDAO implements IDAO<UserVO> {


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

    public UserDAO(){

    }

    @Override
    public UserVO create(UserVO Idless) {
        long id;
        openCurrentSessionWithTransaction();
        id = (long) currentSession.save(Idless);
        closeCurrentSessionWithTransaction();
        return new UserVO(id, Idless);
    }

    @Override
    public void update(UserVO updated) throws IdNotFoundException {
        openCurrentSessionWithTransaction();
        currentSession.update(updated);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(UserVO toBeDeleted) {
        openCurrentSessionWithTransaction();
        currentSession.delete(toBeDeleted);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(long id) {
        Optional<UserVO> toBeDeleted = getById(id);
        if (toBeDeleted.isPresent()) {
            this.delete(toBeDeleted.get());
        }
    }

    @Override
    public List<UserVO> getAll() {
        List<UserVO> retVal = new LinkedList<>();
        openCurrentSession();
        retVal = (List<UserVO>) currentSession.createQuery("from User").getResultList();
        return retVal;

    }

    @Override
    public Optional<UserVO> getById(long id) {
        Optional<UserVO> retVal;
        UserVO u;
        openCurrentSession();
        u = currentSession.get(UserVO.class, id);
        if (u == null) {
            retVal = Optional.empty();
        } else {
            retVal = Optional.of(u);
        }
        return retVal;
    }

    @Override
    public List<UserVO> getByName(String name) {
        List<UserVO> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<UserVO> criteria = builder.createQuery(UserVO.class);
        Root<UserVO> userVORoot = criteria.from(UserVO.class);
        Predicate pred = builder.like(userVORoot.get("name"), name + "*"); //if the artists name starts with the given name
        criteria.select(userVORoot).where(pred);
        TypedQuery<UserVO> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;

    }
}
