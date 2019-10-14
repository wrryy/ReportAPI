package pl.wojrydz.softwareplant.planet;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.wojrydz.softwareplant.api.model.Page;

import java.util.List;

public class PlanetPage implements Page {
    @JsonProperty("next")
    private String nextPage;
    @JsonProperty("results")
    private List<Planet> children;

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public List<Planet> getChildren() {
        return children;
    }

    public void setChildren(List<Planet> children) {
        this.children = children;
    }
}
