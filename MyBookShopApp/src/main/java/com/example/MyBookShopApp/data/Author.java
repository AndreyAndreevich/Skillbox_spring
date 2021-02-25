package com.example.MyBookShopApp.data;

public class Author {
    private Integer id;
    private String name;
    private String biography;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Author{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", biography='" + biography + '\'' +
            '}';
    }
}
