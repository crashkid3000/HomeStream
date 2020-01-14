package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.FilmEntity;
import com.homestream.HomeStream.main.exception.IdNotFoundException;
import com.homestream.HomeStream.vo.ArtistVO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FilmDAO implements IFilmDAO {

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

    public FilmDAO(){

    }

    @Override
    public List<FilmEntity> getByActor(ArtistVO actor) {
        return null;
    }

    @Override
    public List<FilmEntity> getByReleaseDate(LocalDate release) {
        return null;
    }

    @Override
    public List<FilmEntity> getByTags(List<String> tags) {
        return null;
    }

    @Override
    public FilmEntity create(FilmEntity Idless) {
        long id;
        openCurrentSessionWithTransaction();
        id = (long) currentSession.save(Idless);
        closeCurrentSessionWithTransaction();
        return new FilmEntity(id, Idless);
    }

    @Override
    public void update(FilmEntity updated) throws IdNotFoundException {
        openCurrentSessionWithTransaction();
        currentSession.update(updated);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(FilmEntity toBeDeleted) {
        openCurrentSessionWithTransaction();
        currentSession.delete(toBeDeleted);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(long id) {
        Optional<FilmEntity> toBeDeleted = getById(id);
        if(toBeDeleted.isPresent()){
            this.delete(toBeDeleted.get());
        }
    }

    @Override
    public List<FilmEntity> getAll() {
        List<FilmEntity> retVal = new LinkedList<>();
        openCurrentSession();
        retVal = (List<FilmEntity>) currentSession.createQuery("from Film").getResultList();
        return retVal;

    }

    @Override
    public Optional<FilmEntity> getById(long id) {
        Optional<FilmEntity> retVal;
        FilmEntity f;
        openCurrentSession();
        f = currentSession.get(FilmEntity.class, id);
        if(f == null){
            retVal = Optional.empty();
        }
        else {
            retVal = Optional.of(f);
        }
        return retVal;
    }

    @Override
    public List<FilmEntity> getByName(String name) {
        List<FilmEntity> retVal;
        openCurrentSession();
        Criteria criteria = currentSession.createCriteria(FilmEntity.class);
        retVal = (List<FilmEntity>) criteria.add(Restrictions.eq("name", name)).list();
        closeCurrentSession();
        return retVal;
    }
}
