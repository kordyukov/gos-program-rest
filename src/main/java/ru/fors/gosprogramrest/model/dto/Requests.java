package ru.fors.gosprogramrest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Requests {
    private long id;
    private String field;
    private Boolean active;

    public Requests(String field) {
        this.field = field;
    }

}
