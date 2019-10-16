package pl.wojrydz.softwareplant.character;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CharacterPage   {
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

    public List<Character> getChildren() {
        return children;
    }

    public void setChildren(List<Character> children) {
        this.children = children;
    }

    boolean hasNextPage(){
        return !"null".equals(this.getNextPage());
    }
}
