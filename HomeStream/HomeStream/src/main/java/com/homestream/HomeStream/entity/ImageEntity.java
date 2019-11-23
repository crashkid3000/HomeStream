package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.ArtistVO;
import com.homestream.HomeStream.vo.UserVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents everything from a photo to an hand-drawn piece of art
 * @author Justin Braack
 */
public class ImageEntity extends MediaEntity {

    private List<ArtistVO> artists;
    private int[] size;

    /**
     * Creates a new ImageEntitiy
     * @param id The ID of the new entitiy
     * @param name The name of the image (e.g. 'Mona Lisa')
     * @param releaseDate When it was published/created
     * @param lastUpdated When the image was last updated
     * @param fileName Where the image lies within the file system
     * @param fileSize How big the file is
     * @param ownedBy The UserVO who uplaoded the file
     * @param accessibleBy The GroupVO that can access the image
     * @param thumbnailName Where the thumbnail lies (should be identical with <code>name</code>)
     * @param tags The tags for this image
     * @param artists The artists who created this imgae (artist/photographer/...)
     * @param size The size dimensions of this image (<code>[0]</code>:width; <code>[1]</code>: height)
     */
    public ImageEntity(long id, String name, LocalDate releaseDate, LocalDateTime lastUpdated, String fileName, long fileSize, UserVO ownedBy, RoleEntity accessibleBy, String thumbnailName, List<String> tags, List<ArtistVO> artists, int[] size) {
        super(id, name, releaseDate, lastUpdated, fileName, fileSize, ownedBy, accessibleBy, thumbnailName, tags);
        this.artists = artists;
        this.size = size;
    }

    /**
     * Creates a new ImageEntitiy. <i>Use this if you're unsure about the ID, when it was last updated or where the thumbnail lies.</i>
     * @param name The name of the image (e.g. 'Mona Lisa')
     * @param releaseDate When it was published/created
     * @param fileName Where the image lies within the file system
     * @param fileSize How big the file is
     * @param ownedBy The UserVO who uplaoded the file
     * @param accessibleBy The GroupVO that can access the image
     * @param tags The tags for this image
     * @param artists The artists who created this imgae (artist/photographer/...)
     * @param size The size dimensions of this image (<code>[0]</code>:width; <code>[1]</code>: height)
     */
    public ImageEntity(String name, LocalDate releaseDate, String fileName, long fileSize, UserVO ownedBy, RoleEntity accessibleBy, List<String> tags, List<ArtistVO> artists, int[] size) {
        super(name, releaseDate, fileName, fileSize, ownedBy, accessibleBy, fileName, tags);
        this.artists = artists;
        this.size = size;
    }

    /**
     * Returns the artists who have worked on this image
     * @return The artists who have worked on this image
     */
    public List<ArtistVO> getArtists() {
        return artists;
    }

    /**
     * Replaces the list of artists with a new one. Automatically updates the <code>lastUpdated</code> as well.
     * @param artists The updated list of artists who have worked on this image
     */
    public void setArtists(List<ArtistVO> artists) {
        this.artists = artists;
        this.setLastUpdated();
    }

    /**
     * Returns the dimension array representing the size of the image in Pixels
     * @return the dimension array representing the size of the image in Pixels
     * @see ImageEntity#getWidth()
     * @see ImageEntity#getHeight()
     * @see <p>for better/more readable code</p>
     */
    @Deprecated
    public int[] getSize() {
        return size;
    }

    /**
     * Returns the width of the image
     * @return The width of the image
     */
    public int getWidth(){
        return size[0];
    }

    /**
     * Returns the height of the image
     * @return The height of the image
     */
    public int getHeight(){
        return size[1];
    }

    /**
     * Update the image's file path. <i>Automatically updates the <code>lastUpdated</code> and <code>thumbnailName</code></i>
     * @param fileName The upated path where this image can be found. <i>It simultaneously stands for the image's thumbnail.</i>
     */
    @Override
    public void setFileName(String fileName){
        super.setFileName(fileName);
        super.setThumbnailName(fileName);
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
