package pl.wojrydz.softwareplant.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.wojrydz.softwareplant.character.Character;

import java.io.IOException;

public class CharacterSerializer extends JsonSerializer<Character> {

    @Override
    public void serialize(Character value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("character_id", value.getCharacterId());
        gen.writeStringField("character_name", value.getName());
        gen.writeEndObject();
    }
}
