package pl.wojrydz.softwareplant.report;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.wojrydz.softwareplant.api.model.PutRequest;
import pl.wojrydz.softwareplant.character.Character;
import pl.wojrydz.softwareplant.character.CharacterService;
import pl.wojrydz.softwareplant.film.FilmService;
import pl.wojrydz.softwareplant.planet.Planet;
import pl.wojrydz.softwareplant.planet.PlanetService;

import java.util.List;

@Service
class ReportService {

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
        Report databaseRecord = reportRepository.findByReportId(reportId);

        if (databaseRecord != null) {
            return ResponseEntity.ok(databaseRecord);
        }
        return ResponseEntity.notFound().build();
    }

    ResponseEntity putReport(long reportId, PutRequest putRequest) {

        long startTime = System. nanoTime();
        Planet planet = planetService.getPlanet(putRequest.getQuery_criteria_planet_name());
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("planet     : " + estimatedTime);
        startTime = System. nanoTime();
        List<Character> characters = characterService.getCharacters(putRequest.getQuery_criteria_character_phrase(), planet.getUrl());
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("charrssss     : " + estimatedTime);
        startTime = System. nanoTime();
        filmService.enrichCharacterWithFilms(characters);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("filmsss     : " + estimatedTime);

        Report report = createReport(reportId, planet, characters, putRequest);
        saveReport(report);
        return ResponseEntity.ok(report);
    }

    private ResponseEntity<List<Report>> findAll() {
        return ResponseEntity.ok(reportRepository.findAll());
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
        report.setPlanet_name(planet.getTitle());
    }

    private void setReportQueryParams(Report report, PutRequest putRequest) {
        report.setQuery_criteria_character_phrase(putRequest.getQuery_criteria_character_phrase());
        report.setQuery_criteria_planet_name(putRequest.getQuery_criteria_planet_name());
    }

    private void setReportCharacters(Report report, List<Character> characters) {
        report.setCharacters(characters);
    }

    private void saveReport(Report report) {
        Report dbReport = reportRepository.findByReportId(report.getReportId());
        if(dbReport == null){
            dbReport = new Report();
        }
        long id = dbReport.getId();
        BeanUtils.copyProperties(report, dbReport);
        dbReport.setId(id);
        reportRepository.save(dbReport);
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
