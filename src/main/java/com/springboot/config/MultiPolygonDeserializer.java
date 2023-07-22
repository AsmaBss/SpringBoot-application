package com.springboot.config;

import java.io.IOException;


import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class MultiPolygonDeserializer extends JsonDeserializer<MultiPolygon> {
	
	@Override
    public MultiPolygon deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String geoJson = parser.getValueAsString();
        WKTReader reader = new WKTReader();
        try {
            return (MultiPolygon) reader.read(geoJson);
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }

}
