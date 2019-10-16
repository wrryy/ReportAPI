package pl.wojrydz.softwareplant.film;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.wojrydz.softwareplant.api.model.SingleResource;
import pl.wojrydz.softwareplant.utils.FilmSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "films")
@JsonSerialize(using = FilmSerializer.class)
public class Film implements SingleResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    @Transient
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
