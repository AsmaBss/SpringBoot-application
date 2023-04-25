package com.springboot.config;

import java.io.IOException;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    @Override
    public Geometry deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        WKTReader reader = new WKTReader();
        try {
            return reader.read(parser.getValueAsString());
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }
}
