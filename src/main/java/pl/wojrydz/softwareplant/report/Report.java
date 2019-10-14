package pl.wojrydz.softwareplant.report;

import pl.wojrydz.softwareplant.film.PeopleFilm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long report_id;
    private String query_criteria_character_phrase;
    private String query_criteria_planet_name;
    private String planet_id;
    private String planet_name;
    private List<PeopleFilm> characterFilms = new ArrayList<>();

    public long getReport_id() {
        return report_id;
    }

    public void setReport_id(long report_id) {
        this.report_id = report_id;
    }

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

    public String getPlanet_id() {
        return planet_id;
    }

    public void setPlanet_id(String planet_id) {
        this.planet_id = planet_id;
    }

    public String getPlanet_name() {
        return planet_name;
    }

    public void setPlanet_name(String planet_name) {
        this.planet_name = planet_name;
    }

    public List<PeopleFilm> getCharacterFilms() {
        return characterFilms;
    }

    public void setCharacterFilms(List<PeopleFilm> characterFilms) {
        this.characterFilms = characterFilms;
    }
}
