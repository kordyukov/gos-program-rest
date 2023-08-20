package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class Direction {
    private String versionType;
    private TermFinal termFinal;
    private String versionUUID;
    private String creationUser;
    private String lastChangeDate;
    private String creationDate;
    private int versionNumber;
    private TermStart termStart;
    private int number;
    private String lastChangeUser;
    private String name;
    private int id;
}
