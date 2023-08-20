package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Task2 {
    private String versionType;
    private String versionUUID;
    private String creationUser;
    private String lastChangeDate;
    private String creationDate;
    private int versionNumber;
    private int number;
    private String lastChangeUser;
    private String name;
    private int id;
    private ArrayList<Object> linkIndicators;
}
