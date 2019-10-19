package pl.wojrydz.softwareplant.film;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.wojrydz.softwareplant.api.model.SingleResource;
import pl.wojrydz.softwareplant.character.Character;
import pl.wojrydz.softwareplant.utils.FilmSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "filmObjects")
    @JsonIgnore
    private List<Character> characters = new ArrayList<>();
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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.film_id = url.split("/")[5];
    }
    public void addCharacter(Character character){
        this.characters.add(character);
    }
}
