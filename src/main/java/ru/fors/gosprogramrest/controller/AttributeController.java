package ru.fors.gosprogramrest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.fors.gosprogramrest.model.dto.request.Requests;
import ru.fors.gosprogramrest.model.dto.request.RequestsDto;
import ru.fors.gosprogramrest.service.UpdateFieldsService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttributeController {
    private final UpdateFieldsService updateFieldsService;
    @Value("${file-response}")
    private String fileResponse;

    @GetMapping("/checked")
    public String displayCheckboxForm(Model model) throws IOException {
        List<Requests> keys = updateFieldsService.getKeysFromFile();
        RequestsDto requestsDto = new RequestsDto(keys);
        model.addAttribute("allKeys", keys);
        model.addAttribute("request", requestsDto);
        return "checked.html";
    }

    @GetMapping("completed")
    public List<String> getIndex(Model model) throws IOException {

        List<String> keysFromFileByName = updateFieldsService.getKeysFromFileByName(fileResponse);
        model.addAttribute("complete", keysFromFileByName);
        return keysFromFileByName;

    }


}
