package pl.wojrydz.softwareplant.character;

import org.springframework.stereotype.Service;

import pl.wojrydz.softwareplant.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private static final String SWAPI_URL_PEOPLE = "https://swapi.co/api/people/";

    public List<Character> getCharacters(String characterPhrase, String planetUrl) {
        List<Character> characterResult = new ArrayList<>();
        CharacterPage currentCharacterPage = getFirstCharacterPage();

        while (currentCharacterPage.hasNextPage()) {
            List<Character> matchingRecords = findMatch(currentCharacterPage, characterPhrase, planetUrl);
            characterResult.addAll(matchingRecords);
            currentCharacterPage = getCharacterPage(currentCharacterPage.getNextPage());
        }
        return characterResult;
    }

    private CharacterPage getFirstCharacterPage() {
        return getCharacterPage(SWAPI_URL_PEOPLE);
    }

    private CharacterPage getCharacterPage(String swapiUrl) {
        return Utils.callForPage(swapiUrl, CharacterPage.class);
    }

    private List<Character> findMatch(CharacterPage page, String characterPhrase, String planetUrl) {
        return page.getChildren().stream()
                .filter(people -> people.getName().contains(characterPhrase)
                        && people.getPlanetUrl().equals(planetUrl))
                .collect(Collectors.toList());
    }

}
