package pl.wojrydz.softwareplant.planet;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import pl.wojrydz.softwareplant.utils.Utils;

import java.util.Optional;

@Service
public class PlanetService {

    private static final String SWAPI_URL_PLANET = "https://swapi.co/api/planets/";

    private ObjectMapper objectMapper = new ObjectMapper();

    public Planet getPlanet(String query_criteria_planet_name) {
        Planet planet = null;
        PlanetPage currentPlanetPage = getFirstPlanetPage();

        while (planet == null || currentPlanetPage.hasNextPage()) {
            Optional<Planet> matchingRecord = findMatch(currentPlanetPage, query_criteria_planet_name);
            if (matchingRecord.isPresent()) {
                planet = matchingRecord.get();
                break;
            }
            currentPlanetPage = getPlanetPage(currentPlanetPage.getNextPage());
        }
        return planet;
    }

    private PlanetPage getFirstPlanetPage() {
        return getPlanetPage(SWAPI_URL_PLANET);
    }

    private PlanetPage getPlanetPage(String swapiUrl) {
        return Utils.callForPage(swapiUrl, PlanetPage.class);
    }

    private Optional<Planet> findMatch(PlanetPage page, String query_criteria_planet_name) {
        return page.getChildren().stream()
                .filter(planet -> query_criteria_planet_name.equals(planet.getTitle()))
                .findFirst();
    }

}
