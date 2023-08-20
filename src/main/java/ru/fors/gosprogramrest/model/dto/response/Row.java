package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Row {
    private TermFinal termFinal;
    private Csr csr;
    private ArrayList<Object> objects;
    private int number;
    private ExecutorEmployee executorEmployee;
    private int lockMeta;
    private int id;
    private State state;
    private ArrayList<Object> tasks;
    private String versionType;
    private ArrayList<Object> resultTasks;
    private ArrayList<ChildsFlatList> childsFlatList;
    private ArrayList<Indicator> indicators;
    private ArrayList<Object> provisions;
    private boolean isComplex;
    private String approveDate;
    private ArrayList<Target> targets;
    private StateProgram stateProgram;
    private ArrayList<Object> muns;
    private ArrayList<Object> participants;
    private ArrayList<FinancesTotal> financesTotal;
    private int contextYear;
    private TermStart termStart;
    private ArrayList<Object> texts;
    private String versionUUID;
    private ArrayList<Object> buys;
    private ArrayList<Object> documents;
    private String hierarchyId;
    private String uuid;
    private ArrayList<Object> points;
    private ExecutorOffice executorOffice;
    private ArrayList<Object> linkIndicators;
    private CuratorEmployee curatorEmployee;
    private String nodeType;
    private String creationDate;
    private ArrayList<Direction> directions;
    private String lastChangeUser;
    private String name;
    private ArrayList<Object> finances;
    private CuratorOffice curatorOffice;
    private String creationUser;
    private ArrayList<Object> values;
    private ArrayList<Object> legals;
    private int orderHierarchy;
    private String lastChangeDate;
    private int versionNumber;
    private ArrayList<LinkNationalTargetIndicator> linkNationalTargetIndicators;
    private ArrayList<Stage> stages;
}
