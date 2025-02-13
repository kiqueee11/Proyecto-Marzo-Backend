package com.profiles.app.profile_manager_app.serializadores.coordenadas;

import java.io.IOException;

import org.locationtech.jts.geom.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SerializadorPointMapa extends JsonSerializer<Point>{

    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if(point == null){
            jsonGenerator.writeNull();
            return;
        }
        else{
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("latitud", point.getX());
            jsonGenerator.writeNumberField("longitud", point.getY());
            jsonGenerator.writeEndObject();
        }

    }
    
}
