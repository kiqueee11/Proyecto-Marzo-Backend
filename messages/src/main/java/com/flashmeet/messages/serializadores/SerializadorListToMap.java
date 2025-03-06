package com.flashmeet.messages.serializadores;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SerializadorListToMap extends JsonSerializer<List<Map<String, Object>>> {

    @Override
    public void serialize(List<Map<String, Object>> value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {

        if (value == null) {
            gen.writeNull();
            return;
        } else {
            gen.writeStartObject();
            for (Map<String, Object> map : value) {
                gen.writeStartObject();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    gen.writeFieldName(entry.getKey());
                    gen.writeObject(entry.getValue());
                }
                gen.writeEndObject();
            }
            gen.writeEndObject();

        }

    }

}
