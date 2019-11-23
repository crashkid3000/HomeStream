package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.ArtistVO;
import com.homestream.HomeStream.vo.UserVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * This class represents all music tracks
 * @author Justin Braack
 */
public class MusicEntitiy extends MediaEntity{

    private List<ArtistVO> artists;
    private LocalTime length;

    /**
     * Create a new MusicEntitiy
     * @param id The ID of the entity
     * @param name The name of the track (e.g. 'Citizen Erased')
     * @param releaseDate When the track was released
     * @param lastUpdated When it was last updated
     * @param fileName Where it lies in the file system
     * @param fileSize How big the file is
     * @param ownedBy Which <code>UserVO</code> uploaded this?
     * @param accessibleBy Which <code>RoleEntity</code> can access this song
     * @param thumbnailName Where the thumbnail for this music file
     * @param tags The tags for this music piece
     * @param artists Who performs in this song
     * @param length THe length of the song
     */
    public MusicEntitiy(long id, String name, LocalDate releaseDate, LocalDateTime lastUpdated, String fileName, long fileSize, UserVO ownedBy, RoleEntity accessibleBy, String thumbnailName, List<String> tags, List<ArtistVO> artists, LocalTime length) {
        super(id, name, releaseDate, lastUpdated, fileName, fileSize, ownedBy, accessibleBy, thumbnailName, tags);
        this.artists = artists;
        this.length = length;
    }

    /**
     * Create a new MusicEntitiy. <i>Use this if you're unsure about the ID or when it was last updated</i>
     * @param name The name of the track (e.g. 'Citizen Erased')
     * @param releaseDate When the track was released
     * @param fileName Where it lies in the file system
     * @param fileSize How big the file is
     * @param ownedBy Which <code>UserVO</code> uploaded this?
     * @param accessibleBy Which <code>RoleEntity</code> can access this song
     * @param thumbnailName Where the thumbnail for this music file
     * @param tags The tags for this music piece
     * @param artists Who performs in this song
     * @param length THe length of the song
     */
    public MusicEntitiy(String name, LocalDate releaseDate, String fileName, long fileSize, UserVO ownedBy, RoleEntity accessibleBy, String thumbnailName, List<String> tags, List<ArtistVO> artists, LocalTime length) {
        super(name, releaseDate, fileName, fileSize, ownedBy, accessibleBy, thumbnailName, tags);
        this.artists = artists;
        this.length = length;
    }

    /**
     * Returns the musicians performing in this song
     * @return The musicians performing in this song
     */
    public List<ArtistVO> getArtists() {
        return artists;
    }

    /**
     * Set the performing musicians to a new value
     * @param artists The updated list of performing artists
     */
    public void setArtists(List<ArtistVO> artists) {
        this.artists = artists;
        this.setLastUpdated();
    }

    /**
     * Returns the length of the track as a dedicated LocalTime instance
     * @return The length of the track as a dedicated LocalTime instance
     */
    public LocalTime getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; //This is enough, because we only need ID checking - and that's already done in the super class
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //The hash code shall only be dependant on teh ID, and that's alredy done in teh super class
    }
}
