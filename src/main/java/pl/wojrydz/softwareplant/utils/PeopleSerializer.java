package pl.wojrydz.softwareplant.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.wojrydz.softwareplant.people.People;

import java.io.IOException;

public class PeopleSerializer extends JsonSerializer<People> {

    @Override
    public void serialize(People value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
//        gen.writeObjectFieldStart("character");
        gen.writeStringField("character_id", value.getPeople_id());
        gen.writeStringField("character_name", value.getName());
        gen.writeEndObject();
    }
}
