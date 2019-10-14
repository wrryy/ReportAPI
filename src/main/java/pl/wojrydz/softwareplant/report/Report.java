package pl.wojrydz.softwareplant.report;

import pl.wojrydz.softwareplant.character.Character;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportId;
    private String query_criteria_character_phrase;
    private String query_criteria_planet_name;
    private String planet_id;
    private String planet_name;
    @ManyToMany
    private List<Character> characters = new ArrayList<>();

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
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

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
