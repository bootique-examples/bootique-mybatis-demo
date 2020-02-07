package io.bootique.mybatis.demo.model;

public class Painting {

    int id;
    String name;
    int artistId;

    public Painting() {
    }

    public Painting(int id, String name, int artistId) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}
