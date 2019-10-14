package pl.wojrydz.softwareplant.film;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.wojrydz.softwareplant.api.model.SingleResource;
import pl.wojrydz.softwareplant.utils.FilmSerializer;

@JsonSerialize(using = FilmSerializer.class)
public class Film implements SingleResource {

    private String id;
    private String name;
    private String url;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
