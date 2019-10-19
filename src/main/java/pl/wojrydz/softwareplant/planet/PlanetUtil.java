package pl.wojrydz.softwareplant.planet;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import pl.wojrydz.softwareplant.utils.HTTPUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlanetUtil implements HTTPUtils {
    private static final String SWAPI_URL_PLANET = "https://swapi.co/api/planets/";

    @Cacheable("planets")
    public List<Planet> callForPlanets() {
        List<Planet> planets = new ArrayList<>();
        PlanetPage currentPlanetPage = callForFirstPlanetPage();
        do {
            planets.addAll(currentPlanetPage.getChildren());
            currentPlanetPage = callForPlanetPage(currentPlanetPage.getNextPage());
        } while (currentPlanetPage.hasNextPage());
        return planets;
    }

    private PlanetPage callForFirstPlanetPage() {
        return callForPlanetPage(SWAPI_URL_PLANET);
    }

    private PlanetPage callForPlanetPage(String swapiUrl) {
        return callForPage(swapiUrl, PlanetPage.class);
    }
}
