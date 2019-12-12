package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.MusicEntity;
import com.homestream.HomeStream.vo.ArtistVO;

import java.util.List;

public interface IMusicDAO extends IMediaDAO<MusicEntity> {
    List<MusicEntity> getByArtist(ArtistVO musician);
}
