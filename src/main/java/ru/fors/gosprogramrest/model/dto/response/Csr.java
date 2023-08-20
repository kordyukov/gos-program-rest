package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class Csr {
    private String clsId;
    private String creationUser;
    private String hierarchyId;
    private String hierarchyParentId;
    private VersionFinalTime versionFinalTime;
    private String uuid;
    private boolean isFirstVersion;
    private int externalApplicationId;
    private int lockMeta;
    private int id;
    private VersionStartTime versionStartTime;
    private ModificationTimestamp modificationTimestamp;
    private ExistenceFinalTime existenceFinalTime;
    private ExistenceStartTime existenceStartTime;
    private String lastChangeDate;
    private String customGuid;
    private String externalId;
    private String creationDate;
    private String lastChangeUser;
    private String name;
}
