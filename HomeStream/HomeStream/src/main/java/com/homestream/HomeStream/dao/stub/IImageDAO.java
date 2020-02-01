package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.entity.ImageEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.util.List;

public interface IImageDAO extends IMediaDAO<ImageEntity> {
    List<ImageEntity> getByArtist(ArtistVO artist);
}
