package ru.fors.gosprogramrest.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Requests {
    private Integer id;
    private String field;
    private Boolean active;

    public Requests(String field) {
        this.field = field;
    }

}
