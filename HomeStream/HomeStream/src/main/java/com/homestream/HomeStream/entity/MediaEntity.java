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
public abstract class MediaEntity implements IEntitiy {
    private long id;
    private String name;
    private LocalDate releaseDate;
    private LocalDateTime lastUpdated;
    private String fileName;
    private long fileSize;
    private UserVO ownedBy;
    private RoleEntitiy accessibleBy;
    private String thumbnailName;
    private List<String> tags;

    public MediaEntity(long id, String name, LocalDate releaseDate, LocalDateTime lastUpdated, String fileName, long fileSize, UserVO ownedBy, RoleEntitiy accessibleBy, String thumbnailName, List<String> tags) {
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
     * @param name
     * @param releaseDate
     * @param fileName
     * @param fileSize
     * @param ownedBy
     * @param accessibleBy
     * @param thumbnailName
     * @param tags
     */
    public MediaEntity(String name, LocalDate releaseDate, String fileName, long fileSize, UserVO ownedBy, RoleEntitiy accessibleBy, String thumbnailName, List<String> tags) {
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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.lastUpdated = LocalDateTime.now();
    }

    public long getFileSize() {
        return fileSize;
    }

    public UserVO getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(UserVO ownedBy) {
        this.ownedBy = ownedBy;
        this.lastUpdated = LocalDateTime.now();
    }

    public RoleEntitiy getAccessibleBy() {
        return accessibleBy;
    }

    public void setAccessibleBy(RoleEntitiy accessibleBy) {
        this.accessibleBy = accessibleBy;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
        this.lastUpdated = LocalDateTime.now();
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
        this.lastUpdated = LocalDateTime.now();
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
