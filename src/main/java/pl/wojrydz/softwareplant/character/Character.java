package pl.wojrydz.softwareplant.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pl.wojrydz.softwareplant.api.model.SingleResource;
import pl.wojrydz.softwareplant.film.Film;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "characters")
public class Character implements SingleResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column(name = "character_id")
    private String characterId;
    @Column(name = "character_name")
    private String name;
    @ManyToMany
    private List<Film> films;

    @Transient
    private String planetUrl;
    @Transient
    private List<String> filmsUrls;
    @Transient
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("character_id")
    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    @Override
    @JsonProperty("character_name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("films")
    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<String> getFilmsUrls() {
        return filmsUrls;
    }

    @JsonProperty("films")
    public void setFilmsUrls(List<String> filmsUrls) {
        this.filmsUrls = filmsUrls;
    }

    @Override
    @JsonIgnore
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
        this.characterId = url.split("/")[5];
    }
    @JsonIgnore
    public String getPlanetUrl() {
        return planetUrl;
    }

    @JsonProperty("homeworld")
    public void setPlanetUrl(String planetUrl) {
        this.planetUrl = planetUrl;
    }
}
