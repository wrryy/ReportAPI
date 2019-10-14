package pl.wojrydz.softwareplant.report;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.wojrydz.softwareplant.api.model.PutRequest;
import pl.wojrydz.softwareplant.character.Character;
import pl.wojrydz.softwareplant.character.CharacterService;
import pl.wojrydz.softwareplant.film.FilmService;
import pl.wojrydz.softwareplant.planet.Planet;
import pl.wojrydz.softwareplant.planet.PlanetService;

import java.util.List;
import java.util.Optional;

@Service
class ReportService {

    private static final String SWAPI_URL_PEOPLE = "https://swapi.co/api/people/";

    private PlanetService planetService;
    private CharacterService characterService;
    private FilmService filmService;
    private ReportRepository reportRepository;

    public ReportService(PlanetService planetService, CharacterService characterService,
            FilmService filmService, ReportRepository reportRepository) {
        this.planetService = planetService;
        this.characterService = characterService;
        this.filmService = filmService;
        this.reportRepository = reportRepository;
    }

    ResponseEntity<List<Report>> getAll() {
        return findAll();
    }

    ResponseEntity<Report> getOne(long reportId) {
        return getOneFromDatabase(reportId);
    }

    ResponseEntity putReport(long reportId, PutRequest putRequest) {
        Planet planet = planetService.getPlanet(putRequest.getQuery_criteria_planet_name());
        List<Character> characters = characterService.getCharacters(putRequest.getQuery_criteria_character_phrase());
        filmService.enrichCharacterWithFilms(characters);
        Report report = createReport(reportId, planet, characters, putRequest);
        reportRepository.save(report);
        return ResponseEntity.status(201).body(report);
    }

    private ResponseEntity<List<Report>> findAll() {
        return ResponseEntity.ok(reportRepository.findAll());
    }

    private ResponseEntity<Report> getOneFromDatabase(long reportId) {
        Optional<Report> databaseRecord = reportRepository.findById(reportId);
        if (databaseRecord.isPresent()) {
            return ResponseEntity.ok(databaseRecord.get());
        }
        return ResponseEntity.notFound().build();
    }

    private Report createReport(long reportId, Planet planet, List<Character> characters, PutRequest putRequest) {
        Report report = new Report();
        setReportId(report, reportId);
        setReportPlanet(report, planet);
        setReportQueryParams(report, putRequest);
        setReportCharacters(report, characters);
        return report;
    }

    private void setReportId(Report report, long reportId) {
        report.setReportId(reportId);
    }

    private void setReportPlanet(Report report, Planet planet) {
        report.setPlanet_id(planet.getId());
        report.setPlanet_name(planet.getName());
    }

    private void setReportQueryParams(Report report, PutRequest putRequest) {
        report.setQuery_criteria_character_phrase(putRequest.getQuery_criteria_character_phrase());
        report.setQuery_criteria_planet_name(putRequest.getQuery_criteria_planet_name());
    }
    private void setReportCharacters(Report report, List<Character> characters) {
        report.setCharacters(characters);
    }

    ResponseEntity deleteAll() {
        reportRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    ResponseEntity deleteOne(long reportId) {
        reportRepository.deleteById(reportId);
        return ResponseEntity.ok().build();
    }
}
