package com.maxencemacia.jsonWriter.service;

import com.maxencemacia.jsonWriter.model.Model;

import java.io.IOException;
import java.util.List;

public interface PersistenceService {
    void writeModel(List<Model> model) throws IOException;
    List<Model> readModels() throws IOException;
}
