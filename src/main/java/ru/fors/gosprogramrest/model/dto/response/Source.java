package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class Source {
    private String code;
    private String name;
    private int id;
    private String creationUser;
    private String lastChangeDate;
    private String creationDate;
    private String lastChangeUser;
}
