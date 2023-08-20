package ru.fors.gosprogramrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.fors.gosprogramrest.model.dto.Requests;
import ru.fors.gosprogramrest.model.dto.RequestsDto;

import java.util.List;

@Controller
public class SaveRequestController {
    @RequestMapping(value = "/requests/save", method = RequestMethod.POST)
    public List<Requests> postRequest(@ModelAttribute("response") RequestsDto requests){
        requests.toString();
        return null;
    }
}
