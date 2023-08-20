package ru.fors.gosprogramrest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.jknack.handlebars.internal.Files;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fors.gosprogramrest.model.dto.request.ServerRequest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UpdateFieldsServiceImpl implements UpdateFieldsService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final File file;
    @Value("${info.endpoint}")
    private String url;

    private static void traverse(JsonNode node, int level, Map<Object, Object> map, Map<Integer, String> keyBuilder) {
        if (node.getNodeType() == JsonNodeType.ARRAY) {
            traverseArray(node, level, map, keyBuilder);
        } else if (node.getNodeType() == JsonNodeType.OBJECT) {
            traverseObject(node, level, map, keyBuilder);
        } else {
            throw new RuntimeException("Not yet implemented");
        }
    }

    private static void traverseObject(JsonNode node, int level, Map<Object, Object> map, Map<Integer, String> keyBuilder) {
        node.fieldNames().forEachRemaining((String fieldName) -> {
            JsonNode childNode = node.get(fieldName);
            printNode(childNode, fieldName, level, map, keyBuilder);
            //for nested object or arrays
            if (traversable(childNode)) {
                traverse(childNode, level + 1, map, keyBuilder);
            }
        });
    }

    private static void traverseArray(JsonNode node, int level, Map<Object, Object> map, Map<Integer, String> keyBuilder) {
        for (JsonNode jsonArrayNode : node) {
            printNode(jsonArrayNode, "arrayElement", level, map, keyBuilder);
            if (traversable(jsonArrayNode)) {
                traverse(jsonArrayNode, level + 1, map, keyBuilder);
            }
        }
    }

    private static boolean traversable(JsonNode node) {
        return node.getNodeType() == JsonNodeType.OBJECT ||
                node.getNodeType() == JsonNodeType.ARRAY;
    }

    private static void builderKeys(Map<Integer, String> keyBuilder,
                                    int level, String keyName,
                                    Map<Object, Object> map,
                                    JsonNode node) {
        keyBuilder.put(level, keyName);
        String keys = "";
        for (int i = 1; i < level; i++) {
            keys += "%s/".formatted(keyBuilder.get(i));
        }
        map.put(keys + keyName, node.toString());
    }

    private static void printNode(JsonNode node, String keyName, int level, Map<Object, Object> map, Map<Integer, String> keyBuilder) {
        if (traversable(node)) {
//            System.out.printf("%" + (level * 4 - 3) + "s|-- %s=%s type=%s%n",
//                    "", keyName, node, node.getNodeType());
            keyBuilder.put(level, keyName);

            builderKeys(keyBuilder, level, keyName, map, node);

        } else {
            Object value = null;
            if (node.isTextual()) {
                value = node.textValue();
            } else if (node.isNumber()) {
                value = node.numberValue();
            }//todo add more types
//            System.out.printf("%" + (level * 4 - 3) + "s|-- %s=%s type=%s%n",
//                    "", keyName, value, node.getNodeType());
            builderKeys(keyBuilder, level, keyName, map, node);
        }
    }

    @Override
    public Map<Object, Object> receivedFields(Integer year) throws IOException {
//                HttpEntity<ServerRequest> request = new HttpEntity<>(new ServerRequest(year, 1));
//        String read = restTemplate.postForObject(url, request, String.class);
//
        String read = Files.read(new ClassPathResource("/test.json")
                .getFile(), StandardCharsets.UTF_8);

        JsonNode actualObj = objectMapper.readTree(read);
        Map<Object, Object> map = new HashMap<>();
        Map<Integer, String> keyBuilder = new LinkedHashMap<>();
        traverse(actualObj, 1, map, keyBuilder);
        saveToFile(map.keySet());
        return map;
    }

    public void saveToFile(Set<Object> keys) {
        try {
            FileUtils.writeLines(file, keys);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String convertYamlToJson(String yaml) throws JsonProcessingException {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj = yamlReader.readValue(yaml, Object.class);
        ObjectMapper jsonWriter = new ObjectMapper();
        return jsonWriter.writeValueAsString(obj);
    }
}
