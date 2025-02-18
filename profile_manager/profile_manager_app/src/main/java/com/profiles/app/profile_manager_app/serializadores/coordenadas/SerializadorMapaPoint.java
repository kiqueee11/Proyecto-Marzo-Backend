package com.profiles.app.profile_manager_app.serializadores.coordenadas;

import java.io.IOException;
import java.util.Map;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class SerializadorMapaPoint extends JsonDeserializer<Point> {

    @Override
    public Point deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {

        @SuppressWarnings("unchecked")
        Map<String, Double> map = jsonParser.readValueAs(Map.class);
        if (map.containsKey("latitud") && map.containsKey("longitud")) {
            GeometryFactory geometryFactory = new GeometryFactory();
            Point point = geometryFactory.createPoint(new Coordinate(map.get("longitud"), map.get("latitud")));
            point.setSRID(4326);
            return point;
        }

        return null;

    }

}