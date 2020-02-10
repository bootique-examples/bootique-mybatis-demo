package io.bootique.mybatis.demo.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import io.bootique.mybatis.demo.model.Painting;

import java.util.List;

public interface PaintingMapper {

    @Select("SELECT * FROM painting")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "artistId", column = "artist_id")
    })
    List<Painting> getAllPaintings();

    @Select("SELECT * FROM painting WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "artistId", column = "artist_id")
    })
    Painting getPaintingById(int id);

    @Insert("INSERT INTO painting (name, artist_id) VALUES (#{name}, #{artistId})")
    void insertPainting(Painting painting);

}
