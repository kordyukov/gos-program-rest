package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class Value {
    private String creationUser;
    private String lastChangeUser;
    private int year;
    private int owner_id;
    private int creationPrincipal_id;
    private String lastChangeDate;
    private int id;
    private String creationDate;
    private int lastChangePrincipal_id;
    private double value;
    private String versionType;
    private String versionUUID;
    private int versionNumber;
}
