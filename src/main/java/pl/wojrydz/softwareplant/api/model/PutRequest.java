package pl.wojrydz.softwareplant.api.model;

public class PutRequest {
    private String query_criteria_character_phrase;
    private String query_criteria_planet_name;

    public String getQuery_criteria_character_phrase() {
        return query_criteria_character_phrase;
    }

    public void setQuery_criteria_character_phrase(String query_criteria_character_phrase) {
        this.query_criteria_character_phrase = query_criteria_character_phrase;
    }

    public String getQuery_criteria_planet_name() {
        return query_criteria_planet_name;
    }

    public void setQuery_criteria_planet_name(String query_criteria_planet_name) {
        this.query_criteria_planet_name = query_criteria_planet_name;
    }
}
