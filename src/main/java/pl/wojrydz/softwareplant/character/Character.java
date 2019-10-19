package pl.wojrydz.softwareplant.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import pl.wojrydz.softwareplant.api.model.SingleResource;
import pl.wojrydz.softwareplant.film.Film;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "characters")
@JsonPropertyOrder(alphabetic = true)
public class Character implements SingleResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(name = "character_id")
    private String characterId;

    @Column(name = "character_name")
    private String name;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    @JsonProperty("films")
    private List<Film> filmObjects;

    @Transient
    @JsonIgnore
    private String planetUrl;
    @Transient
    private List<String> films;
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

    public List<Film> getFilmObjects() {
        return filmObjects;
    }

    public void setFilmObjects(List<Film> filmObjects) {
        for (Film each: filmObjects){
            each.setCharacter(this);
        }
        this.filmObjects = filmObjects;
    }

    @JsonIgnore
    public List<String> getFilms() {
        return films;
    }

    @JsonProperty("films")
    public void setFilms(List<String> films) {
        this.films = films;
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
