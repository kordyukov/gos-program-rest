package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class Direction2 {
    private String versionType;
    private String versionUUID;
    private TermFinal termFinal;
    private String creationUser;
    private int owner_id;
    private String lastChangeDate;
    private String creationDate;
    private int lastChangePrincipal_id;
    private int versionNumber;
    private TermStart termStart;
    private int number;
    private String lastChangeUser;
    private int creationPrincipal_id;
    private String name;
    private int id;
}
