package pl.wojrydz.softwareplant.film;

import org.springframework.stereotype.Service;

import pl.wojrydz.softwareplant.character.Character;
import pl.wojrydz.softwareplant.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    public void enrichCharacterWithFilms(List<Character> people) {
        for (Character each : people) {
            each.setFilms(enrich(each));
        }
    }

    private List<Film> enrich(Character character) {
        return character.getFilmsUrls().stream().map(this::getFilmResource).collect(Collectors.toList());
    }

    private Film getFilmResource(String filmUrl) {
        Film film = new Film();
        film.setUrl(filmUrl);
        film.setId("1");
        film.setName("FILM STAR WARS");
        return film;
//        return Utils.callForPage(filmUrl, Film.class); //TODO
    }

}
