package pl.wojrydz.softwareplant.film;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import pl.wojrydz.softwareplant.people.People;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "character_films")
@JsonPropertyOrder({ "character", "films"})
public class PeopleFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private People character;
    private List<Film> films;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public People getCharacter() {
        return character;
    }

    public void setCharacter(People character) {
        this.character = character;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
