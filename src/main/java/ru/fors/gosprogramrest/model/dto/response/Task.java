package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Task {
    private String versionType;
    private String versionUUID;
    private String creationUser;
    private int owner_id;
    private String lastChangeDate;
    private ArrayList<Object> linkIndicators;
    private String creationDate;
    private int lastChangePrincipal_id;
    private int versionNumber;
    private int number;
    private String lastChangeUser;
    private int creationPrincipal_id;
    private String name;
    private int id;
}
