package pl.wojrydz.softwareplant.film;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.wojrydz.softwareplant.api.model.Page;

import java.util.List;

public class FilmPage implements Page {

    @JsonProperty("next")
    private String nextPage;
    @JsonProperty("results")
    private List<Film> children;

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public List<Film> getChildren() {
        return children;
    }

    public void setChildren(List<Film> children) {
        this.children = children;
    }
}
