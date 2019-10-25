package com.garret.movies.omdb.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.garret.movies.omdb.dto.OmdbActor;
import com.garret.movies.omdb.util.OmdbMovieUtil;

import java.io.IOException;
import java.util.List;

public class ActorListDeserializer extends StdDeserializer<List<OmdbActor>> {

    protected ActorListDeserializer() {
        super(List.class);
    }

    @Override
    public List<OmdbActor> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String actors = jsonParser.getText();
        return OmdbMovieUtil.actorsToList(actors);
    }
}
