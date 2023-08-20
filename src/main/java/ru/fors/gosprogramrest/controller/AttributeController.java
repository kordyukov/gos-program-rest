package ru.fors.gosprogramrest.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fors.gosprogramrest.model.dto.Engine;
import ru.fors.gosprogramrest.model.dto.Requests;
import ru.fors.gosprogramrest.model.dto.RequestsDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AttributeController {
    @GetMapping("/checked")
    public String displayCheckboxForm(Model model) throws IOException {
        Engine engine = new Engine(true);
        List<Requests> keys = Files.readAllLines(new ClassPathResource("/config-request.yaml")
                        .getFile().toPath(), StandardCharsets.UTF_8)
                .stream()
                .map(Requests::new)
                .toList();
        List<Requests> requestsList = new ArrayList<>();
        keys.iterator().forEachRemaining(requestsList::add);
        model.addAttribute("form", new RequestsDto(requestsList));
        return "index.html";
    }


}
