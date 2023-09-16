package com.rescue.vscube.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rescue.vscube.models.Coordinate;
import org.springframework.stereotype.Component;
import jakarta.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

@Component
public class CoordinateListConverter implements AttributeConverter<List<Coordinate>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Coordinate> coordinates) {
        try {
            return objectMapper.writeValueAsString(coordinates);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting coordinates to JSON", e);
        }
    }

    @Override
    public List<Coordinate> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Coordinate>>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to coordinates", e);
        }
    }
}
