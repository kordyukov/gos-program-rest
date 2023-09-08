package ru.fors.gosprogramrest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fors.gosprogramrest.service.UpdateFieldsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

@Slf4j
@RestController
@RequestMapping("/portal_gp_api")
@RequiredArgsConstructor
public class GosProgramRestController {
    private final UpdateFieldsService updateFieldsService;
    @Value("${file-response}")
    private String fileResponse;

    @GetMapping("/v2/{year}")
    public ResponseEntity<String> getProgramAndFinanceList(@PathVariable("year") Integer year) throws IOException {

        List<Map<Object, Object>> objectObjectMap = updateFieldsService.receivedFields(year);

        List<String> keysFromFileByName = updateFieldsService.getKeysFromFileByName(fileResponse);

        List<Map<String, Object>> response = new ArrayList<>();

        for (Map<Object, Object> objectObjectMap1 : objectObjectMap) {
            Map<String, Object> map = new HashMap<>();
            objectObjectMap1.keySet().forEach(key -> {
                for (String responseField : keysFromFileByName) {
                    if (key.equals(responseField)) {
                        map.put(valueOf(key), objectObjectMap1.get(key));
                    }
                }
            });
            response.add(map);
        }

        JsonNode jsonNode = new ObjectMapper().convertValue(response.toString(), JsonNode.class);

        return ResponseEntity.ok(jsonNode.asText());
    }

}
