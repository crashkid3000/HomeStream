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

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
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
        List<FilmEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<FilmEntity> criteria = builder.createQuery(FilmEntity.class);
        Root<FilmEntity> filmEntityRoot = criteria.from(FilmEntity.class);
        Root<ArtistVO> artistVoRoot
        EntityType<FilmEntity> FilmEntity_ = filmEntityRoot.getModel();
        EntityType<ArtistVO> ArtistVO_ =
        Join<FilmEntity, ArtistVO> filmArtistJoin = filmEntityRoot.join("ArtistVO");
        Predicate pred = builder.equal(filmEntityRoot.get("mainActors"), release); //if the artists name starts with the given name
        criteria.select(filmEntityRoot).where(pred);
        TypedQuery<FilmEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal
    }

    @Override
    public List<FilmEntity> getByReleaseDate(LocalDate release) {
        List<FilmEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<FilmEntity> criteria = builder.createQuery(FilmEntity.class);
        Root<FilmEntity> filmEntityRoot = criteria.from(FilmEntity.class);
        Predicate pred = builder.equal(filmEntityRoot.get("releaseDate"), release); //if the artists name starts with the given name
        criteria.select(filmEntityRoot).where(pred);
        TypedQuery<FilmEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;
    }

    @Override
    public List<FilmEntity> getByTags(List<String> tags) {
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
        List<FilmEntity> retVal = this.getAll();
        List<FilmEntity> tempList = new LinkedList<>();
        for(String tag: tags){
            for(FilmEntity f: retVal){
                if(f.getTags().contains(tag)){
                    tempList.add(f);
                }
            }
            retVal = new LinkedList<>(tempList);
            tempList = new LinkedList();
        }

        return retVal;

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
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<FilmEntity> criteria = builder.createQuery(FilmEntity.class);
        Root<FilmEntity> filmEntityRoot = criteria.from(FilmEntity.class);
        Predicate pred = builder.like(filmEntityRoot.get("name"), name + "*"); //if the artists name starts with the given name
        criteria.select(filmEntityRoot).where(pred);
        TypedQuery<FilmEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;

        //Old code with deprecated method calls:
//        List<FilmEntity> retVal;
//        openCurrentSession();
//        Criteria criteria = currentSession.createCriteria(FilmEntity.class);
//        retVal = (List<FilmEntity>) criteria.add(Restrictions.eq("name", name)).list();
//        closeCurrentSession();
//        return retVal;
    }
}
