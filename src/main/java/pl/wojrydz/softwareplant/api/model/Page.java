package pl.wojrydz.softwareplant.api.model;

import java.util.List;

public interface Page {
    <T extends SingleResource> List<T> getChildren();
}
