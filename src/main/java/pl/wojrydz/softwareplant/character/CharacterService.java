package pl.wojrydz.softwareplant.character;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.wojrydz.softwareplant.error.ApplicationException;
import pl.wojrydz.softwareplant.utils.HTTPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private CharacterUtil characterUtil;

    public CharacterService(CharacterUtil characterUtil) {
        this.characterUtil = characterUtil;
    }

    public List<Character> getCharacters(String characterPhrase, String planetUrl) {
        List<Character> characterResult;
        List<Character> characters = characterUtil.callForCharacters();
        characterResult = findMatch(characters, characterPhrase, planetUrl);
        if(characterResult.isEmpty()){
            throw new ApplicationException("No character matches query param.");
        }
        return characterResult;
    }

    private List<Character> findMatch(List<Character> characters, String characterPhrase, String planetUrl) {
        return characters.stream()
                .filter(character -> character.getName().contains(characterPhrase)
                        && character.getPlanetUrl().equals(planetUrl))
                .collect(Collectors.toList());
    }

}
