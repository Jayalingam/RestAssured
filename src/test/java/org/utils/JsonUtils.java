package org.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> readJsonAsMap(String filePath) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
    }
}
