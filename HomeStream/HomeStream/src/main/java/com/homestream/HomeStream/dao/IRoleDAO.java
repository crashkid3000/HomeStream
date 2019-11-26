package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.RoleEntity;

import java.util.List;

/**
 * The interface definition of the RoleDAO
 * @author Justin Braack
 */
public interface IRoleDAO {
    /**
     * Creates a new RoleEntitiy in the persistence layer
     * @param roleEntitiy the new role entitiy
     * @return the same entity as before, but with an updated <code>id</code>
     */
    RoleEntity create(RoleEntity roleEntitiy);

    /**
     * Deletes the specified RoleEntity from the persistence layer
     * @param toBeDeleted the <code>RoleEntitiy</code> that shall be deleted
     */
    void delete(RoleEntity toBeDeleted);

    /**
     * Deletes the <code>RoleEntitiy</code> with the given <code>id</code>
     * @param id The ID of the entitiy that shall be deleted
     */
    void delete(int id);

    /**
     * Returns all entitites present in the persistence layer
     * @return A list of all the entities known to the persistence layer
     */
    List<RoleEntity> getAll();

    /**
     * Get the <code>RoleEntity</code> with a specific <code>id</code>
     * @param id The ID of the <code>RoleEntity</code> that shall be returned
     * @return The <code>RoleEntitiy</code> with that <code>id</code>
     */
    RoleEntity getById(long id);

    /**
     * Gets every <code>RoleEntity</code> where the gien string matches its name
     * @param name The name to look for
     * @return All entities where their name matches the given <code>name</code> (in part)
     */
    List<RoleEntity> getByName(String name);
}
