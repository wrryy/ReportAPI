package pl.wojrydz.softwareplant.character;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.wojrydz.softwareplant.api.model.Page;

import java.util.List;

public class CharacterPage implements Page {
    @JsonProperty("next")
    private String nextPage;
    @JsonProperty("results")
    private List<Character> children;

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public List<Character> getChildren() {
        return children;
    }

    public void setChildren(List<Character> children) {
        this.children = children;
    }
}