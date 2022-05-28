package com.fgsantana.datacaching.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    Double rating;

    public Game(Integer id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Game() {}

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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id) && Objects.equals(name, game.name) && Objects.equals(rating, game.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating);
    }
}
