package ru.fors.gosprogramrest.service;

import ru.fors.gosprogramrest.model.dto.Requests;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UpdateFieldsService {
    Map<Object, Object> receivedFields(Integer year) throws IOException;

    List<Requests> getKeysFromFile() throws IOException;

    List<String> getKeysFromFileByName(String fileName) throws IOException;

    void saveToFile(Collection<?> keys, File file);
}
