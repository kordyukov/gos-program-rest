package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class StateProgram {
    private String versionType;
    private String versionUUID;
    private String creationUser;
    private String lastChangeDate;
    private String creationDate;
    private String uuid;
    private int versionNumber;
    private int number;
    private String lastChangeUser;
    private String name;
    private int lockMeta;
    private int id;
}
