package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class Okei {
    private String clsId;
    private String creationUser;
    private OpeningTime openingTime;
    private VersionFinalTime versionFinalTime;
    private String uuid;
    private ClosureTime closureTime;
    private int externalApplicationId;
    private int lockMeta;
    private int id;
    private VersionStartTime versionStartTime;
    private String lastChangeDate;
    private String fullName;
    private String externalId;
    private String creationDate;
    private String lastChangeUser;
    private String name;
}
