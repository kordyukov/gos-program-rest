package ru.fors.gosprogramrest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class RequestsDto {
    private List<Requests> requestsList;

    public void addRequests(Requests requests) {
        requestsList.add(requests);
    }

    public RequestsDto(List<Requests> requestsList) {
        this.requestsList = requestsList;
    }
}
