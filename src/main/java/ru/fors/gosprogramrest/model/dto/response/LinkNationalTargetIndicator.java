package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class LinkNationalTargetIndicator {
    private String versionType;
    private String versionUUID;
    private String creationUser;
    private int owner_id;
    private String lastChangeDate;
    private String creationDate;
    private int lastChangePrincipal_id;
    private int versionNumber;
    private int nationalTarget_id;
    private String lastChangeUser;
    private int creationPrincipal_id;
    private int id;
}
