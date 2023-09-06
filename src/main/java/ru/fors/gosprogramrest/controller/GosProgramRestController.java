package ru.fors.gosprogramrest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fors.gosprogramrest.service.UpdateFieldsService;

import java.io.IOException;
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
    public ResponseEntity<JSONObject> getProgramAndFinanceList(@PathVariable("year") Integer year) throws IOException {

        Map<Object, Object> objectObjectMap = updateFieldsService.receivedFields(year);

        List<String> keysFromFileByName = updateFieldsService.getKeysFromFileByName(fileResponse);

        Map<String, Object> response = new HashMap<>();

        objectObjectMap.keySet().forEach(key -> {
            for (String responseField : keysFromFileByName) {
                if (key.equals(responseField)) response.put(valueOf(key), objectObjectMap.get(key));
            }
        });

        return ResponseEntity.ok(new JSONObject(response));
    }

}
