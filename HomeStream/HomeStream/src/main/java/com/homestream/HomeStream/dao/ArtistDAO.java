package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.main.exception.IdNotFoundException;
import com.homestream.HomeStream.vo.ArtistVO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ArtistDAO implements IDAO<ArtistVO> {

    private Session currentSession;
    private Transaction currentTransaction;

    /**
     * Gets a SessionFactory
     * @return a SessionFactory
     */
    private static SessionFactory getSessionFactory(){
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder ssrBuilder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        SessionFactory sFactory = cfg.buildSessionFactory(ssrBuilder.build());
        return sFactory;
    }

    /**
     * Opens a new Session
     * @return The newly opened Session
     */
    protected Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    /**
     * Opens a new Session and prepares a transaction, ready for use
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

    public ArtistDAO(){

    }

    @Override
    public ArtistVO create(ArtistVO Idless) {
        long id;
        openCurrentSessionWithTransaction();
        id = (long) currentSession.save(Idless);
        closeCurrentSessionWithTransaction();
        return new ArtistVO(id, Idless);

    }

    @Override
    public void update(ArtistVO updated) throws IdNotFoundException {
        openCurrentSessionWithTransaction();
        currentSession.update(updated);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(ArtistVO toBeDeleted) {
        openCurrentSessionWithTransaction();
        currentSession.delete(toBeDeleted);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(long id) {
        Optional<ArtistVO> toBeDeleted = getById(id);
        if(toBeDeleted.isPresent()){
            openCurrentSessionWithTransaction();
            currentSession.delete(toBeDeleted.get());
            closeCurrentSessionWithTransaction();
        }

    }

    @Override
    public List<ArtistVO> getAll() {
        List<ArtistVO> retVal;
        openCurrentSession();
        retVal = (List<ArtistVO>) currentSession.createQuery("from Artist").list();
        closeCurrentSession();
        return retVal;
    }

    @Override
    public Optional<ArtistVO> getById(long id) {
        Optional<ArtistVO> retVal;
        ArtistVO a;
        openCurrentSession();
        a = currentSession.get(ArtistVO.class, id);
        closeCurrentSession();
        if(a != null){
            retVal = Optional.of(a);
        }
        else {
            retVal = Optional.empty();
        }

        return retVal;
    }

    @Override
    public List<ArtistVO> getByName(String name) {
        List<ArtistVO> retVal;
        openCurrentSession();
        Criteria criteria = currentSession.createCriteria(ArtistVO.class);
        retVal = (List<ArtistVO>) criteria.add(Restrictions.eq("name", name)).list();
        closeCurrentSession();
        if(retVal == null){
            System.out.println("ArtistDAO.getByName(): retVal is null! Returning a new LinkedList<>() instead...");
            retVal = new LinkedList<>();
        }
        return retVal;
    }
}
