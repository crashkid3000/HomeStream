package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.dao.IMediaDAO;
import com.homestream.HomeStream.entity.ImageEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.util.List;

public interface IImageDAO extends IMediaDAO<ImageEntity> {
    List<ImageEntity> getByArtist(ArtistVO artist);
}
