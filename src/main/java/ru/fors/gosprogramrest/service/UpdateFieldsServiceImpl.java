package ru.fors.gosprogramrest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fors.gosprogramrest.model.dto.request.Request;
import ru.fors.gosprogramrest.model.dto.request.Requests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class UpdateFieldsServiceImpl implements UpdateFieldsService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final File fileRequest;
    @Value("${info.endpoint}")
    private String url;
    @Value("${file-request}")
    private String fileRead;

    public UpdateFieldsServiceImpl(RestTemplate restTemplate,
                                   ObjectMapper objectMapper,
                                   @Qualifier("fileRequest") File fileRequest) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.fileRequest = fileRequest;
    }


    private static void traverse(JsonNode node, int level, Map<Object, Object> map, Map<Integer, String> keyBuilder) {
        if (node.getNodeType() == JsonNodeType.ARRAY) {
            traverseArray(node, level, map, keyBuilder);
        } else if (node.getNodeType() == JsonNodeType.OBJECT) {
            traverseObject(node, level, map, keyBuilder);
        } else {
            traverseObject(node, level, map, keyBuilder);
            //throw new RuntimeException("Not yet implemented");
        }
    }

    private static void traverseObject(JsonNode node, int level, Map<Object, Object> map, Map<Integer, String> keyBuilder) {
        node.fieldNames().forEachRemaining((String fieldName) -> {
            JsonNode childNode = node.get(fieldName);
            printNode(childNode, fieldName, level, map, keyBuilder);
            if (traversable(childNode)) {
                traverse(childNode, level + 1, map, keyBuilder);
            }
        });
    }

    private static void traverseArray(JsonNode node, int level, Map<Object, Object> map, Map<Integer, String> keyBuilder) {
        for (JsonNode jsonArrayNode : node) {
            printNode(jsonArrayNode, "--Корневой узел--", level, map, keyBuilder);
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
            keyBuilder.put(level, keyName);

            builderKeys(keyBuilder, level, keyName, map, node);

        } else {
            Object value = null;
            if (node.isTextual()) {
                value = node.textValue();
            } else if (node.isNumber()) {
                value = node.numberValue();
            }
            builderKeys(keyBuilder, level, keyName, map, node);
        }
    }

    @Override
    public List<Map<Object, Object>> receivedFields(Integer year) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity http = new HttpEntity(new Request(), headers);
        String read = restTemplate.postForObject(url, http, String.class);
//        String read = Files.read(new ClassPathResource("/test.json")
//                .getFile(), StandardCharsets.UTF_8);

        JsonNode actualObj = objectMapper.readTree(read);
        Iterator<JsonNode> elements = actualObj.elements();
        List<Map<Object, Object>> maps = new ArrayList<>();
        while (elements.hasNext()) {
            JsonNode next = elements.next();
            Map<Object, Object> map = new HashMap<>();
            Map<Integer, String> keyBuilder = new LinkedHashMap<>();
            traverse(next, 1, map, keyBuilder);
            maps.add(map);
        }

        maps.forEach(map -> {
            if (!map.isEmpty()) {
                saveToFile(map.keySet(), fileRequest);
            }
        });

        return maps;
    }

    @Override
    public List<Requests> getKeysFromFile() throws IOException {
        return java.nio.file.Files.readAllLines(new ClassPathResource(fileRead)
                        .getFile().toPath(), StandardCharsets.UTF_8)
                .stream()
                .map(Requests::new)
                .toList();
    }

    @Override
    public List<String> getKeysFromFileByName(String fileName) throws IOException {
        return java.nio.file.Files.readAllLines(new ClassPathResource(fileName)
                        .getFile().toPath(), StandardCharsets.UTF_8)
                .stream()
                .toList();
    }

    @Override
    public void saveToFile(Collection<?> keys, File file) {
        try {
            FileUtils.writeLines(file, keys);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
