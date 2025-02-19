package com.profiles.app.profile_manager_app.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class PointParser {



    public static Point parsePoint(String posicion) {
        String[] coordinates = posicion.split(",");
        double latitude = Double.parseDouble(coordinates[0]);
        double longitude = Double.parseDouble(coordinates[1]);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point resultado = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        resultado.setSRID(4326);
        return resultado;

    }
    
}
