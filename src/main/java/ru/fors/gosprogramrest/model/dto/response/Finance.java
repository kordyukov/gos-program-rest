package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Finance {
    private String versionType;
    private String versionUUID;
    private String creationUser;
    private ArrayList<Value> values;
    private String lastChangeDate;
    private Office office;
    private Source source;
    private String creationDate;
    private int versionNumber;
    private String lastChangeUser;
    private int id;
}
