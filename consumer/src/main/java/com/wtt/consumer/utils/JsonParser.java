package com.wtt.consumer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonParser {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private JsonParser() {
        // hidden constructor
    }

    public static <G> String mapToJson(G object) {
        String json;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public static <G> G mapToObject(String json, Class<G> target) {
        G object;
        try {
            object = objectMapper.readValue(json, target);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
