package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.ImageEntity;
import com.homestream.HomeStream.main.exception.IdNotFoundException;
import com.homestream.HomeStream.vo.ArtistVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ImageDAO implements IImageDAO {

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

    public ImageDAO(){

    }


    @Override
    public List<ImageEntity> getByArtist(ArtistVO actor) {
        //TODO ka ob das hier funktionoiert
        List<ImageEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<ImageEntity> criteria = builder.createQuery(ImageEntity.class);
        Root<ImageEntity> imageEntityRoot = criteria.from(ImageEntity.class);
        //Root<ArtistVO> artistVoRoot
        EntityType<ImageEntity> ImageEntity_ = imageEntityRoot.getModel();
        //EntityType<ArtistVO> ArtistVO_ =
        Join<ImageEntity, ArtistVO> imageArtistJoin = imageEntityRoot.join("ArtistVO");
        Predicate pred = builder.equal(imageEntityRoot.get("artists"), actor);
        criteria.select(imageEntityRoot).where(pred);
        TypedQuery<ImageEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;
    }

    @Override
    public List<ImageEntity> getByReleaseDate(LocalDate release) {
        List<ImageEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<ImageEntity> criteria = builder.createQuery(ImageEntity.class);
        Root<ImageEntity> imageEntityRoot = criteria.from(ImageEntity.class);
        Predicate pred = builder.equal(imageEntityRoot.get("releaseDate"), release);
        criteria.select(imageEntityRoot).where(pred);
        TypedQuery<ImageEntity> query = currentSession.createQuery(criteria);
        retVal = query.getResultList();
        closeCurrentSession();
        return retVal;
    }

    @Override
    public List<ImageEntity> getByTags(List<String> tags) {
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
        List<ImageEntity> retVal = this.getAll();
        List<ImageEntity> tempList = new LinkedList<>();
        for(String tag: tags){
            for(ImageEntity i: retVal){
                if(i.getTags().contains(tag)){
                    tempList.add(i);
                }
            }
            retVal = new LinkedList<>(tempList);
            tempList = new LinkedList<>();
        }

        return retVal;

    }

    @Override
    public ImageEntity create(ImageEntity Idless) {
        long id;
        openCurrentSessionWithTransaction();
        id = (long) currentSession.save(Idless);
        closeCurrentSessionWithTransaction();
        return new ImageEntity(id, Idless);
    }

    @Override
    public void update(ImageEntity updated) throws IdNotFoundException {
        openCurrentSessionWithTransaction();
        currentSession.update(updated);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(ImageEntity toBeDeleted) {
        openCurrentSessionWithTransaction();
        currentSession.delete(toBeDeleted);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(long id) {
        Optional<ImageEntity> toBeDeleted = getById(id);
        if(toBeDeleted.isPresent()){
            this.delete(toBeDeleted.get());
        }
    }

    @Override
    public List<ImageEntity> getAll() {
        List<ImageEntity> retVal = new LinkedList<>();
        openCurrentSession();
        retVal = (List<ImageEntity>) currentSession.createQuery("from Image").getResultList();
        return retVal;

    }

    @Override
    public Optional<ImageEntity> getById(long id) {
        Optional<ImageEntity> retVal;
        ImageEntity i;
        openCurrentSession();
        i = currentSession.get(ImageEntity.class, id);
        if(i == null){
            retVal = Optional.empty();
        }
        else {
            retVal = Optional.of(i);
        }
        return retVal;
    }

    @Override
    public List<ImageEntity> getByName(String name) {
        List<ImageEntity> retVal;
        openCurrentSession();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<ImageEntity> criteria = builder.createQuery(ImageEntity.class);
        Root<ImageEntity> imageEntityRoot = criteria.from(ImageEntity.class);
        Predicate pred = builder.like(imageEntityRoot.get("name"), name + "*"); //if the artists name starts with the given name
        criteria.select(imageEntityRoot).where(pred);
        TypedQuery<ImageEntity> query = currentSession.createQuery(criteria);
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
