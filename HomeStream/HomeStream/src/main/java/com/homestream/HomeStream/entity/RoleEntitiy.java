package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.UserVO;

import java.util.List;
import java.util.Objects;

public class RoleEntitiy implements IEntitiy{

    private long id;
    private String name;
    private List<UserVO> users;

    /**
     * Creates a new RoleEntitiy instance
     * @param id
     * @param name
     * @param users
     */
    public RoleEntitiy(long id, String name, List<UserVO> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    /**
     * Creates a new RoleEntitiy instance. <i>Use this if you don't know the id</i>
     * @param name
     * @param users
     */
    public RoleEntitiy(String name, List<UserVO> users) {
        this.id = -1;
        this.name = name;
        this.users = users;
    }

    /**
     * Performs an equality check based on an ID
     * @param o The object that shall be compared with
     * @return Whether the given Object <code>o</code> and this are identical (see above)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntitiy that = (RoleEntitiy) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Return this object into a string repesentation; useful for development
     * @return A string repesentation of this object
     */
    @Override
    public String toString() {
        return "RoleEntitiy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<UserVO> getUsers() {
        return users;
    }
}
