package pl.wojrydz.softwareplant.planet;

import org.springframework.stereotype.Service;
import pl.wojrydz.softwareplant.error.ApplicationException;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private PlanetUtil planetUtil;

    public PlanetService(PlanetUtil planetUtil) {
        this.planetUtil = planetUtil;
    }

    public Planet getPlanet(String query_criteria_planet_name) {
        Planet planet = null;
        List<Planet> planets = planetUtil.callForPlanets();
        Optional<Planet> matchingRecord = findMatch(planets, query_criteria_planet_name);
        if (matchingRecord.isPresent()) {
            return matchingRecord.get();
        }
        throw new ApplicationException("There is no planet with such name.");
    }


    private Optional<Planet> findMatch(List<Planet> planets, String query_criteria_planet_name) {
        return planets.stream()
                .filter(planet -> query_criteria_planet_name.equals(planet.getName()))
                .findFirst();
    }

}
