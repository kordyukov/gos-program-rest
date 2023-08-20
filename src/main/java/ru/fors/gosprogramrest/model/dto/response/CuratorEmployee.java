package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class CuratorEmployee {
    private Office office;
    private String uuid;
    private boolean isFirstVersion;
    private String rank;
    private int lockMeta;
    private int id;
    private ExistenceStartTime existenceStartTime;
    private String customGuid;
    private String creationDate;
    private String firstName;
    private String patronymic;
    private String lastChangeUser;
    private String name;
    private String lastName;
    private String creationUser;
    private VersionFinalTime versionFinalTime;
    private VersionStartTime versionStartTime;
    private ExistenceFinalTime existenceFinalTime;
    private String lastChangeDate;
    private String entityDiscriminator;
}
