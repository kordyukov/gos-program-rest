package ru.fors.gosprogramrest.model.dto.request;

public record ServerRequest(
        Integer contextYear,
        Integer progVersion
) {
}
