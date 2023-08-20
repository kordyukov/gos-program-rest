package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FinancesTotal {
    private ArrayList<Value> values;
    private Office office;
    private Source source;
}
