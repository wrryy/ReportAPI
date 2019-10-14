package pl.wojrydz.softwareplant.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.wojrydz.softwareplant.film.Film;

import java.io.IOException;

public class FilmSerializer extends JsonSerializer<Film> {

    @Override
    public void serialize(Film value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("film_id", value.getId());
        gen.writeStringField("film_name", value.getName());
        gen.writeEndObject();
    }
}
