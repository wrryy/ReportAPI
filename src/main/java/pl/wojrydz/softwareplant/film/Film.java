package pl.wojrydz.softwareplant.film;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.wojrydz.softwareplant.api.model.SingleResource;
import pl.wojrydz.softwareplant.character.Character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "films")
@JsonSerialize(using = FilmSerializer.class)
public class Film implements SingleResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private String film_id;
    private String title;
    @ManyToOne
    @JoinColumn(name="character_id")
    @JsonIgnore
    private Character character;
    @Transient
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    @Override
    public String getName() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.film_id = url.split("/")[5];
    }
}
