package pl.wojrydz.softwareplant.planet;

import pl.wojrydz.softwareplant.api.model.SingleResource;

public class Planet implements SingleResource {
    private String name;
    private String url;
    private String id;

    public String getTitle() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.id = url.split("/")[5];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
