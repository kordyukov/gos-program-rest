package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class Target {
    private String versionType;
    private int number;
    private String versionUUID;
    private String creationUser;
    private String lastChangeUser;
    private String name;
    private String lastChangeDate;
    private int id;
    private String creationDate;
    private int versionNumber;
}
