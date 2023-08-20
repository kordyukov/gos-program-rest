package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Indicator {
    private String versionUUID;
    private double valueCurrent6;
    private double valueCurrent7;
    private double valueCurrent4;
    private double valueCurrent5;
    private double valueCurrent2;
    private double valueCurrent3;
    private double valueCurrent1;
    private int number;
    private double valueCurrent10;
    private ExecutorEmployee executorEmployee;
    private double baseValue;
    private double valueCurrent8;
    private double valueCurrent9;
    private String dynamic;
    private int id;
    private double valueCurrent12;
    private double valueCurrent11;
    private String versionType;
    private ExecutorOffice executorOffice;
    private String creationDate;
    private Task task;
    private Okei okei;
    private String lastChangeUser;
    private String name;
    private BaseDate baseDate;
    private ArrayList<Object> works;
    private String creationUser;
    private ArrayList<Value> values;
    private ArrayList<Object> bases;
    private ArrayList<Object> muns;
    private String lastChangeDate;
    private int versionNumber;
    private boolean isAnalytic;
    private boolean isWork;
    private ArrayList<Object> linkNationalTargetIndicators;
    private Target target;
}
