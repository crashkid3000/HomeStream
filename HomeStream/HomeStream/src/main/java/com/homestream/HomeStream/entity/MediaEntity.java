package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.UserVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A template class that groups common properties for a media resource together
 * @author Justin Braack
 */
public abstract class MediaEntity implements IEntity {
    private long id;
    private String name;
    private LocalDate releaseDate;
    private LocalDateTime lastUpdated;
    private String fileName;
    private long fileSize;
    private UserVO ownedBy;
    private List<RoleEntity> accessibleBy;
    private String thumbnailName;
    private List<String> tags;

    /**
     * Creates a new MediaEntity template
     * @param id The ID of this media piece
     * @param name The title gven to this media piece
     * @param releaseDate When this media piece was published/created
     * @param lastUpdated When this media piece recieved its last update
     * @param fileName Where it is located in the file system
     * @param fileSize How big this media piece is
     * @param ownedBy Who uploaded the media piece
     * @param accessibleBy By whom this pecia piece can be accessed by
     * @param thumbnailName Where this media piece's thumbnail lies in teh file system
     * @param tags The tags assigned to this media piece
     */
    public MediaEntity(long id, String name, LocalDate releaseDate, LocalDateTime lastUpdated, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.lastUpdated = lastUpdated;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.ownedBy = ownedBy;
        this.accessibleBy = accessibleBy;
        this.thumbnailName = thumbnailName;
        this.tags = tags;
    }

    /**
     * Creates a new MediaEntity template. <i>Use this if you don't know the id, or when it was last updated</i>
     * @param name The title gven to this media piece
     * @param releaseDate When this media piece was published/created
     * @param fileName Where it is located in the file system
     * @param fileSize How big this media piece is
     * @param ownedBy Who uploaded the media piece
     * @param accessibleBy By whom this pecia piece can be accessed by
     * @param thumbnailName Where this media piece's thumbnail lies in teh file system
     * @param tags The tags assigned to this media piece
     */
    public MediaEntity(String name, LocalDate releaseDate, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags) {
        this.id = -1;
        this.name = name;
        this.releaseDate = releaseDate;
        this.lastUpdated = LocalDateTime.now();
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.ownedBy = ownedBy;
        this.accessibleBy = accessibleBy;
        this.thumbnailName = thumbnailName;
        this.tags = tags;
    }

    /**
     * Returns this entitiy's ID
     * @return This entitiy's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the hame/title of this media piece
     * @return The hame/title of this media piece
     */
    public String getName() {
        return name;
    }

    /**
     * Gives this media piece a new name/tile. Automatically updates the <code>lastUpdated</code> as well.
     * @param name The updated name/title
     */
    public void setName(String name) {
        this.name = name;
        this.lastUpdated = LocalDateTime.now();
    }

    /**
     * Returns when this media piece was released/created
     * @return The <code>LocalDate</code> on which this media piece was released/created
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Give this media piece an updated date on which it was released/created, e.g. when it goes from "created" to "published". Automatically updates the <code>lastUpdated</code> as well.
     * @param releaseDate The new date on which it was released/updated
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        this.lastUpdated = LocalDateTime.now();
    }

    /**
     * Returns when this media piece received it's latest update
     * @return The exact <code>LocalDateTime</code> when the last update occurred
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the lastUpdated to the current LocalDateTime
     */
    protected void setLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }

    /**
     * Returns the location on which this media piece lies within th file system
     * @return The location on which this media piece lies within th file system
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Update the file path on which this media piece lies. Automatically updates the <code>lastUpdated</code> as well.
     * @param fileName The upated path where this media piece can be found
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.setLastUpdated();
    }

    /**
     * Returns the file size of this media piece
     * @return The file size of this media piece
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Returns whoever uploaded this media piece
     * @return Whoever uploaded this media piece
     */
    public UserVO getOwnedBy() {
        return ownedBy;
    }

    /**
     * Sets the new owner of this file. Automatically updates the <code>lastUpdated</code> as well.
     * @param ownedBy The new owner of this file
     */
    public void setOwnedBy(UserVO ownedBy) {
        this.ownedBy = ownedBy;
        this.setLastUpdated();
    }

    /**
     * Returns which group of users can access this media piece
     * @return Which group of users can access this media piece
     */
    public List<RoleEntity> getAccessibleBy() {
        return accessibleBy;
    }

    /**
     * Update the group of users which can access this media piece. Automatically updates the <code>lastUpdated</code> as well.
     * @param accessibleBy The updated <code>RoleEntitiy</code> that can access this media piece
     */
    public void setAccessibleBy(List<RoleEntity> accessibleBy) {
        this.accessibleBy = accessibleBy;
        this.setLastUpdated();
    }

    /**
     * Returns where the thumbnail for this media piece lies within the file system
     * @return Where the thumbnail for this media piece lies within the file system
     */
    public String getThumbnailName() {
        return thumbnailName;
    }

    /**
     * Updates where the thumbnail for this media piece can be found. Automatically updates the <code>lastUpdated</code> as well.
     * @param thumbnailName The updated location of the thumbnail in the file system
     */
    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
        this.setLastUpdated();
    }

    /**
     * Returns the list of tags associated with this media piece
     * @return The list of tags associated with this media piece
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Updates the list of tags with a new one. Automatically updates the <code>lastUpdated</code> as well.
     * @param tags The updated list of tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
        this.setLastUpdated();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaEntity that = (MediaEntity) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "MediaEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", lastUpdated=" + lastUpdated +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", ownedBy=" + ownedBy +
                ", accessibleBy=" + accessibleBy +
                ", thumbnailName='" + thumbnailName + '\'' +
                ", tags=" + tags +
                '}';
    }


}
