package com.homestream.HomeStream.vo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A value object representing an artist (musician, actor, painter, soemthing inbetween, ...)
 * @author Justin Braack
 */
public class ArtistVO {

    private long dbid;
    private String name;
    private LocalDate bday;

    public ArtistVO(long dbid, String name, LocalDate bday) {
        this.dbid = dbid;
        this.name = name;
        this.bday = bday;
    }

    /**
     * Creates a new ArtistVO instance. <i>Use this if you're unsure about the dbid</i>
     * @param name
     * @param bday
     */
    public ArtistVO(String name, LocalDate bday) {
        this.name = name;
        this.bday = bday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistVO artistVO = (ArtistVO) o;
        return dbid == artistVO.dbid &&
                name.equals(artistVO.name) &&
                bday.equals(artistVO.bday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbid, name, bday);
    }

    @Override
    public String toString() {
        return "ArtistVO{" +
                "dbid=" + dbid +
                ", name='" + name + '\'' +
                ", bday=" + bday +
                '}';
    }

    @Override
    public Object clone()
    {
        ArtistVO cloned = new ArtistVO(this.getDbid(), this.getName(), this.getBday());
        return cloned;
    }

    public long getDbid() {
        return dbid;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBday() {
        return bday;
    }
}
