package ru.fors.gosprogramrest.model.dto.response;

import lombok.Data;

@Data
public class Time {
    private int hour;
    private int minute;
    private int second;
    private int nano;
}
