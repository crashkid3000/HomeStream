package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.ArtistVO;
import com.homestream.HomeStream.vo.UserVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * An entity that represents everything between a homemade film to a blockbuster movie
 * @author Justin Braack
 */
public class FilmEntitiy extends MediaEntity {

    private List<ArtistVO> mainActors;
    private List<ArtistVO> sideActors;
    private LocalTime length;

    /**
     * Createa a new FilmEntitiy.
     * @param id
     * @param name
     * @param releaseDate
     * @param lastUpdated
     * @param fileName
     * @param fileSize
     * @param ownedBy
     * @param accessibleBy
     * @param thumbnailName
     * @param tags
     * @param mainActors
     * @param sideActors
     * @param length
     */
    public FilmEntitiy(long id, String name, LocalDate releaseDate, LocalDateTime lastUpdated, String fileName, long fileSize, UserVO ownedBy, RoleEntitiy accessibleBy, String thumbnailName, List<String> tags, List<ArtistVO> mainActors, List<ArtistVO> sideActors, LocalTime length) {
        super(id, name, releaseDate, lastUpdated, fileName, fileSize, ownedBy, accessibleBy, thumbnailName, tags);
        this.mainActors = mainActors;
        this.sideActors = sideActors;
        this.length = length;
    }

    /**
     * Creates a new FilmEntity instance. <i>Use this if you don't know the id, or when it was last updated</i>
     * @param name
     * @param releaseDate
     * @param fileName
     * @param fileSize
     * @param ownedBy
     * @param accessibleBy
     * @param thumbnailName
     * @param tags
     * @param length
     * @param mainActors
     * @param sideActors
     */
    public FilmEntitiy(String name, LocalDate releaseDate, String fileName, long fileSize, UserVO ownedBy, RoleEntitiy accessibleBy, String thumbnailName, List<String> tags, List<ArtistVO> mainActors, List<ArtistVO> sideActors, LocalTime length) {
        super(name, releaseDate, fileName, fileSize, ownedBy, accessibleBy, thumbnailName, tags);
        this.mainActors = mainActors;
        this.sideActors = sideActors;
        this.length = length;
    }
}
