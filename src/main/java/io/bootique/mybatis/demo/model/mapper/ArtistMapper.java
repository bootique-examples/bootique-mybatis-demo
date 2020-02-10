package io.bootique.mybatis.demo.model.mapper;

import io.bootique.mybatis.demo.model.Artist;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArtistMapper {

    @Select("SELECT * FROM artist")
    List<Artist> getAllArtists();

    @Select("SELECT * FROM artist WHERE id = #{id}")
    Artist getArtistById(int id);

    @Insert("INSERT INTO artist (name) VALUES (#{name})")
    void insertArtist(Artist artist);
}
