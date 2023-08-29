package ru.fors.gosprogramrest.model.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class RequestsDto {
    private List<Requests> requests;

    public RequestsDto(List<Requests> keys) {
        if (keys != null) {
            IntStream.range(0, keys.size()).forEach(i -> keys.get(i).setId(i));
        }
        this.requests = keys;
    }

    public void addRequests(Requests requests) {
        this.requests.add(requests);
    }
}
