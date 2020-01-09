package com.homestream.HomeStream.dao.stub;

import com.homestream.HomeStream.entity.FilmEntity;
import com.homestream.HomeStream.entity.ImageEntity;
import com.homestream.HomeStream.entity.MusicEntity;
import com.homestream.HomeStream.entity.RoleEntity;
import com.homestream.HomeStream.vo.ArtistVO;
import com.homestream.HomeStream.vo.UserVO;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBStub {
    public static List<UserVO> users;
    public static List<RoleEntity> roles;
    public static List<ArtistVO> artists;
    public static List<FilmEntity> films;
    public static List<MusicEntity> musics;
    public static List<ImageEntity> images;
    public static Path dataDir = FileSystems.getDefault().getPath("res", "data").toAbsolutePath();

    public static void setupDB(){
        if (null == users){
            users = new ArrayList<UserVO>();
            users.add(new UserVO(0, "Jon Doe", "jon@doe.com", "HASHED:password"));
            users.add(new UserVO(1, "Max Muster", "musst@er.los", "HASHED:\""));
            users.add(new UserVO(2, "Norman Ritter", "nritter@koethen.de", "HASHED:meinefische"));
            users.add(new UserVO(3, "Justin Braack", "bananenkennstdunicht@ost.en", "HASHED:admin"));
        }

        if(null == roles){
            roles = new ArrayList<RoleEntity>();
            List<UserVO> someUsers = new LinkedList<UserVO>();
            someUsers.add(users.get(1));
            someUsers.add(users.get(3));
            roles.add(new RoleEntity(0, "leere Rolle", null));
            roles.add(new RoleEntity(1, "Rolle mit Usern", someUsers));

        }

        if(null == artists)
        {
            artists = new ArrayList<ArtistVO>();
            artists.add(new ArtistVO(0, "Empirion", LocalDate.of(1993, 1, 1)));
            artists.add(new ArtistVO(1, "TheFatRat", LocalDate.of(1979, 6, 1)));
            artists.add(new ArtistVO(2, "crashkid3000", LocalDate.of(1998, 7, 6)));
            artists.add(new ArtistVO(3, "Lauren Brehm", LocalDate.of(1990, 8, 5)));
            artists.add(new ArtistVO(4, "Fear Factory", LocalDate.of(1989, 1, 1)));
            artists.add(new ArtistVO(5, "r/place", LocalDate.of(2017, 4, 1)));
        }

        if(null == films)
        {
            films = new ArrayList<>();
            LinkedList<ArtistVO> __ownerAtomflick = new LinkedList<ArtistVO>();
            __ownerAtomflick.add(artists.get(2));
            LinkedList<ArtistVO> __mainActorsCars = new LinkedList<ArtistVO>();
            __mainActorsCars.add(artists.get(4));
            LinkedList<RoleEntity> __roleAtomflick = new LinkedList<>();
            __roleAtomflick.add(roles.get(1));
            LinkedList<RoleEntity> __roleCars = new LinkedList<>();
            __roleCars.add(roles.get(0));

            films.add(new FilmEntity(0, "Atomflick", LocalDate.of(2019, 11, 23), LocalDateTime.now(), "Csgo Atomflicc-1.m4v", 19281365, users.get(3), __roleAtomflick, null, new LinkedList<String>(), __ownerAtomflick, null, LocalTime.of(0, 0, 19)));
            films.add(new FilmEntity(1, "csgo", LocalDate.of(1999, 8, 28), LocalDateTime.now(), "Bumm-1.mp4", 15872449, users.get(3), __roleCars, null, new LinkedList<String>(), __mainActorsCars, null, LocalTime.of(0, 3, 19)));
        }

        if(null == musics){
            musics = new ArrayList<>();
            LinkedList<ArtistVO> __artistMonody = new LinkedList<>();
            LinkedList<ArtistVO> __artistGamma = new LinkedList<>();
            LinkedList<RoleEntity> __roleMonody = new LinkedList<>();
            LinkedList<RoleEntity> __roleGamma = new LinkedList<>();
            List<String> __tagsRammstein = new LinkedList();
            List<String> __tagsRammstein2 = new LinkedList();
            __artistMonody.add(artists.get(1));
            __artistMonody.add(artists.get(3));
            __artistGamma.add(artists.get(0));
            __roleMonody.add(roles.get(1));
            __roleGamma.add(roles.get(0));
            __tagsRammstein.add("rammstein");
            __tagsRammstein2.add("rock");

            musics.add(new MusicEntity(0, "Monody", LocalDate.of(2015, 11, 7), LocalDateTime.now(), "TheFatRat - Monody (feat. Laura Brehm).mp3", 6996742, users.get(0), __roleMonody, "defettjeratte_thumbnail.jpg", new LinkedList<String>(), __artistMonody, LocalTime.of(0, 4, 51)));
            musics.add(new MusicEntity(1, "Gamma", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "Demolition Racer.m4a", 4395643, users.get(1), __roleGamma, "demolition_racer.png", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));

            musics.add(new MusicEntity(2, "Deutschland", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "01 Deutschland.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", __tagsRammstein, __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(3, "Radio", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "02 Radio.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", __tagsRammstein2, __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(4, "Zeig Dich", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "03 Zeig Dich.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(5, "Ausländer", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "04 Ausländer.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(6, "Sex", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "05 Sex.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(7, "Puppe", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "06 Puppe.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(8, "Was Ich Liebe", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "07 Was Ich Liebe.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(9, "Diamant", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "08 Diamant.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(10, "Weit Weg", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "09 Weit Weg.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(11, "Tattoo", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "10 Tattoo.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(12, "Hallomann", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "11 Hallomann.mp3", 4395643, users.get(1), __roleGamma, "rammstein-artwork.jfif", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));

            musics.add(new MusicEntity(13, "Huh Hah Dschinghis Khan", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "01 Huh Hah Dschinghis Khan.mp3", 4395643, users.get(1), __roleGamma, "51jPcVpDNLL._SY355_.jpg", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(14, "Tarzan Boy", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "01 Tarzan Boy.mp3", 4395643, users.get(1), __roleGamma, "81jMLg3JN9L._SX355_.jpg", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
            musics.add(new MusicEntity(15, "The Look", LocalDate.of(1999, 1, 1), LocalDateTime.now(), "01 The Look.mp3", 4395643, users.get(1), __roleGamma, "R-3063436-1314008184.jpeg.jpg", new LinkedList<String>(), __artistGamma, LocalTime.of(0, 4, 29)));
        }

        if (null == images)
        {
            images = new ArrayList<>();
            LinkedList<ArtistVO> __artistCk3k = new LinkedList<>();
            LinkedList<ArtistVO> __artistReddit = new LinkedList<>();
            LinkedList<RoleEntity> __roleGRE = new LinkedList<>();
            LinkedList<RoleEntity> __rolerPlace = new LinkedList<>();
            __artistCk3k.add(artists.get(2));
            __artistReddit.add(artists.get(5));
            __roleGRE.add(roles.get(0));
            __rolerPlace.add(roles.get(1));

            images.add(new ImageEntity(0, "Global Retrowave Elite", LocalDate.of(2019, 9, 15), LocalDateTime.now(), "gre_with_chrome_bl.png", 410376, users.get(3), __roleGRE, null, new LinkedList<String>(), __artistCk3k, new int[]{1000, 400}));
            images.add(new ImageEntity(1, "rPlace", LocalDate.of(2017, 4, 3), LocalDateTime.now(), "rPlace.png", 842435, users.get(2), __rolerPlace, null, new LinkedList<String>(), __artistReddit, new int[]{4000, 4000}));
        }


    }
}
