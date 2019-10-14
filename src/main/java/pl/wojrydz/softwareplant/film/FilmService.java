package pl.wojrydz.softwareplant.film;

import org.springframework.stereotype.Service;

import pl.wojrydz.softwareplant.people.People;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilmService {

    public Map<People, List<Film>> getFilmsPerPeople(List<People> people) {
        Map<People,List<Film>> filmResult = new HashMap<>();

        for (People each : people) {
            filmResult.put(each, getFilmsPerPeople(each));
        }
        return filmResult;
    }

    private List<Film> getFilmsPerPeople(People people){
        return people.getFilms().stream().map(this::getFilmResource).collect(Collectors.toList());
    }

    private Film getFilmResource(String filmUrl){
        Film film = new Film();
        film.setUrl(filmUrl);
        film.setId("1");
        film.setName("FILM STAR WARS");
        return film;
//        return Utils.callForPage(filmUrl, Film.class);
    }

}
