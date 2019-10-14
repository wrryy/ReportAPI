package pl.wojrydz.softwareplant.report;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.wojrydz.softwareplant.api.model.PutRequest;
import pl.wojrydz.softwareplant.film.Film;
import pl.wojrydz.softwareplant.film.FilmService;
import pl.wojrydz.softwareplant.film.PeopleFilm;
import pl.wojrydz.softwareplant.people.People;
import pl.wojrydz.softwareplant.people.PeopleService;
import pl.wojrydz.softwareplant.planet.Planet;
import pl.wojrydz.softwareplant.planet.PlanetService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
class ReportService {

    private static final String SWAPI_URL_PEOPLE = "https://swapi.co/api/people/";

    private PlanetService planetService;
    private PeopleService peopleService;
    private FilmService filmService;
    private ReportRepository reportRepository;

    public ReportService(PlanetService planetService, PeopleService peopleService,
            FilmService filmService, ReportRepository reportRepository) {
        this.planetService = planetService;
        this.peopleService = peopleService;
        this.filmService = filmService;
        this.reportRepository = reportRepository;
    }

    ResponseEntity putReport(long report_id, PutRequest putRequest) {
        Planet planet = planetService.getPlanet(putRequest.getQuery_criteria_planet_name());
        List<People> people = peopleService.getPeople(putRequest.getQuery_criteria_character_phrase());
        Map<People, List<Film>> filmsPerPeople = filmService.getFilmsPerPeople(people);
        Report report = createReport(planet, filmsPerPeople, putRequest, report_id);
        reportRepository.save(report);
        return ResponseEntity.ok(report);
    }

    private Report createReport(Planet planet, Map<People, List<Film>> filmsPerPeople, PutRequest putRequest, long report_id) {
        Report report = new Report();
        setReportId(report, report_id);
        setReportPlanet(report, planet);
        setReportQueryParams(report, putRequest);
        setReportPeopleFilm(report, filmsPerPeople);
        return report;
    }

    private void setReportId(Report report, long report_id) {
        report.setReport_id(report_id);
    }
    private void setReportPlanet(Report report, Planet planet){
        report.setPlanet_id(planet.getId());
        report.setPlanet_name(planet.getName());
    }

    private void setReportQueryParams(Report report, PutRequest putRequest){
        report.setQuery_criteria_character_phrase(putRequest.getQuery_criteria_character_phrase());
        report.setQuery_criteria_planet_name(putRequest.getQuery_criteria_planet_name());
    }

    private void setReportPeopleFilm(Report report, Map<People, List<Film>> filmsPerPeople ){
        List<PeopleFilm> peopleFilms = report.getCharacterFilms();
        for (Map.Entry<People, List<Film>> each : filmsPerPeople.entrySet()) {
            PeopleFilm peopleFilm = createPeopleFilm(each);
            peopleFilms.add(peopleFilm);
        }
    }

    private PeopleFilm createPeopleFilm(Map.Entry<People, List<Film>> entry){
        PeopleFilm peopleFilm = new PeopleFilm();
        peopleFilm.setCharacter(entry.getKey());
        peopleFilm.setFilms(entry.getValue());
        return peopleFilm;
    }
}
