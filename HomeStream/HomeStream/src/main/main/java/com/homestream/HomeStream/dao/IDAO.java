package com.homestream.HomeStream.dao;

import java.util.List;
import java.util.Optional;

/**
 * A generic interface describing the minimum set of operations each data access object (DAO) should be able to perform
 * @author Justin Braack
 * @param <T> The type the DAO deals with. Specified at implementation level, e.g. by <code>RubberDuckDAO <b>extends</b> IDAO&lt;RubberDuck&gt;</code>
 */
public interface IDAO<T> {

    /**
     * Creates a new entry in the persistence layer
     * @param Idless the new  entitiy that shall be written. Its <code>id</code> doesn't have to be set.
     * @return the same entity as before, but with an updated <code>id</code>
     */
    T create(T Idless);

    /**
     * Deletes the specified item from the persistence layer
     * @param toBeDeleted the object that shall be deleted
     */
    void delete(T toBeDeleted);

    /**
     * Deletes the item in the persistence layer with the given <code>id</code>
     * @param id The ID of the object that shall be deleted
     */
    void delete(long id);

    /**
     * Returns all objects of the required type present in the persistence layer
     * @return A list of all the entities of said type known to the persistence layer. If none are found, an empty list is returned.
     */
    List<T> getAll();

    /**
     * Get the object with a specific <code>id</code>
     * @param id The ID of the object that shall be returned
     * @return The object with that <code>id</code>, hidden inside the Optional that is returned. If none if found, Optional.empty() is returned
     */
    Optional<T> getById(long id);

    List<T> getByName(String name);
}
