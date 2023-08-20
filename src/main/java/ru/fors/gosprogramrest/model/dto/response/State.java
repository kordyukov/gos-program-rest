package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class State {
    private String uuid;
    private String number;
    private int lockMeta;
    private int id;
    private String executorRule;
    private boolean isSystem;
    private boolean nonBlocked;
    private String lastChangeUser;
    private String name;
    private boolean userSelfModerate;
    private VersionFinalTime versionFinalTime;
    private boolean selfModerate;
    private VersionStartTime versionStartTime;
    private String lastChangeDate;
    private boolean userNonBlocked;
}
