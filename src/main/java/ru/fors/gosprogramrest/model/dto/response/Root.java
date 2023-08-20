package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class Root {
    private boolean success;
    private List<Row> rows;
}
