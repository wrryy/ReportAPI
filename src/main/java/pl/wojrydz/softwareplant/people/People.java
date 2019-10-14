package pl.wojrydz.softwareplant.people;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.wojrydz.softwareplant.api.model.SingleResource;
import pl.wojrydz.softwareplant.utils.PeopleSerializer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "people")
@JsonSerialize(using = PeopleSerializer.class)
public class People implements SingleResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private String people_id;
    private String name;
    @Transient
    private List<String> films;
    @Transient
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public String getUrl() {
        return url;
    }

    public String getPeople_id() {
        return people_id;
    }

    public void setPeople_id(String people_id) {
        this.people_id = people_id;
    }

    public void setUrl(String url) {
        this.url = url;
        this.people_id = url.split("/")[5];
    }
}
