package com.homestream.HomeStream.vo;

import java.util.Objects;

/**
 * Represents a user of HomeStream
 * @author JustinBraack
 */
public class UserVO implements IValueObject {

    private long dbid;
    private String name;
    private String email;
    private String password;

    /**
     * Initialize with default values. Shall only be used by <code>clone()</code>
     */
    private UserVO() {
        this.dbid = -1;
        this.name = "";
        this.email = "";
        this.password = "";
    }

    public UserVO(long dbid, String name, String email, String password) {
        this.dbid = dbid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the name of the user
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email address
     * @return The user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the hashed password of the user
     * @return The hashed password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the ID of this VO in the database
     * @return The ID of this VO in the database
     */
    public long getDbid() {
        return dbid;
    }

    @Override
    public boolean equals(Object o)
    {
        UserVO casted;

        if (o == null){
            return false;
        }
        if (this == o){
            return true;
        }
        if (this.getClass() != o.getClass())
        {
            return false;
        }

        casted = (UserVO)o;

        if(this.hashCode() != casted.hashCode()){
            return false;
        }
        if(this.getDbid() != casted.getDbid()){
            return false;
        }
        if(!this.getEmail().equals(casted.getEmail())){
            return false;
        }
        if(!this.getName().equals(casted.getName())){
            return false;
        }
        if(!this.getPassword().equals(casted.getName())){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbid, name, email, password);
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "dbid=" + dbid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Object clone() {
        UserVO clone = new UserVO();
        clone.dbid = this.getDbid();
        clone.email = this.getName();
        clone.name = this.getName();
        clone.password = this.getPassword();
        return clone;
    }


}
