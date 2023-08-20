package ru.fors.gosprogramrest.service;

import java.io.IOException;
import java.util.Map;

public interface UpdateFieldsService {
    Map<Object, Object> receivedFields(Integer year) throws IOException;
}
