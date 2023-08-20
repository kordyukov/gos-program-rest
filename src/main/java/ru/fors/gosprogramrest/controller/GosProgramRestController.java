package ru.fors.gosprogramrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fors.gosprogramrest.service.UpdateFieldsService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/portal_gp_api")
@RequiredArgsConstructor
public class GosProgramRestController {
    private final UpdateFieldsService updateFieldsService;

    @GetMapping("/v2/{year}")
    public ResponseEntity<String> getProgramAndFinanceList(@PathVariable("year") Integer year) throws IOException, ClassNotFoundException {

        Map<Object, Object> objectObjectMap = updateFieldsService.receivedFields(year);

        List<String> keys = Files.readAllLines(new ClassPathResource("/response-fields")
                .getFile().toPath(), StandardCharsets.UTF_8);

        Map<String, Object> result = keys.stream()
                .collect(Collectors.toMap(key -> key, objectObjectMap::get, (a, b) -> b));

        return ResponseEntity.ok(new ObjectMapper().writeValueAsString(result));
    }

}
