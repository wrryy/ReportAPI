package pl.wojrydz.softwareplant.people;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.wojrydz.softwareplant.api.model.Page;

import java.util.List;

public class PeoplePage implements Page {
    @JsonProperty("next")
    private String nextPage;
    @JsonProperty("results")
    private List<People> children;

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public List<People> getChildren() {
        return children;
    }

    public void setChildren(List<People> children) {
        this.children = children;
    }
}
