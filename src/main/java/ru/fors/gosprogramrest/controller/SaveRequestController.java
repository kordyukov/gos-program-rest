package ru.fors.gosprogramrest.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fors.gosprogramrest.model.dto.Requests;
import ru.fors.gosprogramrest.model.dto.RequestsDto;
import ru.fors.gosprogramrest.service.UpdateFieldsService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SaveRequestController {
    private final UpdateFieldsService updateFieldsService;
    private final File fileResponse;

    public SaveRequestController(UpdateFieldsService updateFieldsService,
                                 @Qualifier("fileResponse") File fileResponse) {
        this.updateFieldsService = updateFieldsService;
        this.fileResponse = fileResponse;
    }

    @PostMapping("/requests/save")
    public String postRequest(@ModelAttribute("request") RequestsDto requests) throws IOException {
        List<String> result = new ArrayList<>();

        List<Integer> ids = requests.getRequests()
                .stream()
                .map(Requests::getField)
                .map(Integer::parseInt)
                .toList();

        List<Requests> keysFromFile = updateFieldsService.getKeysFromFile();

        ids.forEach(id -> {
            result.add(keysFromFile.get(id).getField());
        });

        updateFieldsService.saveToFile(result, fileResponse);

        return "redirect:/completed";
    }


}
