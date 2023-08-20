package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class LinkIndicator {
    private Indicator indicator;
    private String versionType;
    private String versionUUID;
    private String creationUser;
    private int owner_id;
    private String lastChangeDate;
    private String creationDate;
    private int lastChangePrincipal_id;
    private int versionNumber;
    private String lastChangeUser;
    private int creationPrincipal_id;
    private int id;
    private int indicator_id;
}
