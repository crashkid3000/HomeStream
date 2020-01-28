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

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MusicDAO implements IMusicDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session currentSession;
    private Transaction currentTransaction;

    /**
     * Gets a SessionFactory
     *
     * @return a SessionFactory
     */
    /*
    private static SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder ssrBuilder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        SessionFactory sFactory = cfg.buildSessionFactory(ssrBuilder.build());
        return sFactory;
    }*/

    /**
     * Opens a new Session
     *
     * @return The newly opened Session
     */
    protected Session openCurrentSession() {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }

    /**
     * Opens a new Session and prepares a transaction, ready for use
     *
     * @return The newly opened Session
     */
    protected Session openCurrentSessionWithTransaction() {
        currentSession = sessionFactory.openSession();
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

    public MusicDAO() {

    }

    @Override
    public List<MusicEntity> getByArtist(ArtistVO actor) {
        //TODO ka ob das hier funktionoiert
        List<MusicEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<MusicEntity> criteria = builder.createQuery(MusicEntity.class);
        Root<MusicEntity> musicEntityRoot = criteria.from(MusicEntity.class);
        //Root<ArtistVO> artistVoRoot
        EntityType<MusicEntity> ImageEntity_ = musicEntityRoot.getModel();
        //EntityType<ArtistVO> ArtistVO_ =
        Join<MusicEntity, ArtistVO> imageArtistJoin = musicEntityRoot.join("ArtistVO");
        Predicate pred = builder.equal(musicEntityRoot.get("artists"), actor);
        criteria.select(musicEntityRoot).where(pred);
        TypedQuery<MusicEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;
    }

    @Override
    public List<MusicEntity> getByReleaseDate(LocalDate release) {
        List<MusicEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<MusicEntity> criteria = builder.createQuery(MusicEntity.class);
        Root<MusicEntity> musicEntityRoot = criteria.from(MusicEntity.class);
        Predicate pred = builder.equal(musicEntityRoot.get("releaseDate"), release);
        criteria.select(musicEntityRoot).where(pred);
        TypedQuery<MusicEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;
    }

    @Override
    public List<MusicEntity> getByTags(List<String> tags) {
        /* Instead of creating criteria, predicates etc and then conducting a search query, we're doing things differently here:
         *   - get all apt Entitys from the DB (yes, I know it is memory-heavy. However, it is by far the easier solution
         *       due to the way tags are saved in the DB, in entities and how we deal with them in the rest of the application)
         *   - go through each given tag
         *     - look if each film has said tag
         *     - if so, it stays in the list
         *     - if not, it is thrown out
         *
         *  This approach means that if there are a lot of entries in the DB that also contain a lot of tags, this search will be
         * quite slow at the beginning. However, it at least speeds up with every next given tag... :s
         */
        List<MusicEntity> retVal = this.getAll();
        List<MusicEntity> tempList = new LinkedList<>();
        for (String tag : tags) {
            for (MusicEntity m : retVal) {
                if (m.getTags().contains(tag)) {
                    tempList.add(m);
                }
            }
            retVal = new LinkedList<>(tempList);
            tempList = new LinkedList<>();
        }

        return retVal;

    }

    @Override
    public MusicEntity create(MusicEntity Idless) {
        long id;
        openCurrentSessionWithTransaction();
        id = (long) currentSession.save(Idless);
        closeCurrentSessionWithTransaction();
        return new MusicEntity(id, Idless);
    }

    @Override
    public void update(MusicEntity updated) throws IdNotFoundException {
        openCurrentSessionWithTransaction();
        currentSession.update(updated);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(MusicEntity toBeDeleted) {
        openCurrentSessionWithTransaction();
        currentSession.delete(toBeDeleted);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(long id) {
        Optional<MusicEntity> toBeDeleted = getById(id);
        if (toBeDeleted.isPresent()) {
            this.delete(toBeDeleted.get());
        }
    }

    @Override
    public List<MusicEntity> getAll() {
        List<MusicEntity> retVal = new LinkedList<>();
        openCurrentSession();
        retVal = (List<MusicEntity>) currentSession.createQuery("from Music").getResultList();
        return retVal;

    }

    @Override
    public Optional<MusicEntity> getById(long id) {
        Optional<MusicEntity> retVal;
        MusicEntity m;
        openCurrentSession();
        m = currentSession.get(MusicEntity.class, id);
        if (m == null) {
            retVal = Optional.empty();
        } else {
            retVal = Optional.of(m);
        }
        return retVal;
    }

    @Override
    public List<MusicEntity> getByName(String name) {
        List<MusicEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<MusicEntity> criteria = builder.createQuery(MusicEntity.class);
        Root<MusicEntity> musicEntityRoot = criteria.from(MusicEntity.class);
        Predicate pred = builder.like(musicEntityRoot.get("name"), name + "*"); //if the artists name starts with the given name
        criteria.select(musicEntityRoot).where(pred);
        TypedQuery<MusicEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;

    }
}