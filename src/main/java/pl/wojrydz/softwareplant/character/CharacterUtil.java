package pl.wojrydz.softwareplant.character;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import pl.wojrydz.softwareplant.utils.HTTPUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterUtil implements HTTPUtils {
    private static final String SWAPI_URL_PEOPLE = "https://swapi.co/api/people/";

//    @Cacheable("characters")
    List<Character> callForCharacters(){
        List<Character> characterResult = new ArrayList<>();
        CharacterPage currentCharacterPage = callForFirstCharacterPage();
        do {
            characterResult.addAll(currentCharacterPage.getChildren());
            currentCharacterPage = callForCharacterPage(currentCharacterPage.getNextPage());
        }
        while (currentCharacterPage.hasNextPage());
        return characterResult;
    }

    private CharacterPage callForFirstCharacterPage() {
        return callForCharacterPage(SWAPI_URL_PEOPLE);
    }

    private CharacterPage callForCharacterPage(String swapiUrl) {
        return callForPage(swapiUrl, CharacterPage.class);
    }

}
