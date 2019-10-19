package pl.wojrydz.softwareplant.film;

import org.springframework.stereotype.Service;
import pl.wojrydz.softwareplant.character.Character;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private FilmUtil filmUtil;

    public FilmService(FilmUtil filmUtil) {
        this.filmUtil = filmUtil;
    }

    public void enrichCharacterWithFilms(List<Character> people) {
        for (Character each : people) {
            each.setFilmObjects(enrich(each));
        }
    }

    private List<Film> enrich(Character character) {
        return character.getFilms().stream().map(this::getFilmResource).collect(Collectors.toList());
    }

    private Film getFilmResource(String filmUrl) {
        return filmUtil.callForPage(filmUrl, Film.class);
    }

}
