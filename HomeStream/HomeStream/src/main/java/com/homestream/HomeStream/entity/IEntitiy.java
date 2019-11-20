package com.homestream.HomeStream.entity;

/**
 * "Template" for all Entities
 * @author Justin Braack
 */
public interface IEntitiy
{
    /**
     * Performs an equality check based on an ID
     * @param o The object that shall be compared with
     * @return Whether the given Object <code>o</code> and this are identical (see above)
     */
    boolean equals(Object o);

    /**
     * Return this object into a string repesentation; useful for development
     * @return A string repesentation of this object
     */
    String toString();
}