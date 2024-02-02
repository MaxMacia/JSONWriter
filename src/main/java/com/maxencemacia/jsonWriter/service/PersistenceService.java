package com.maxencemacia.jsonWriter.service;

import com.maxencemacia.jsonWriter.model.Model;

import java.io.IOException;
import java.util.List;

public interface PersistenceService {
    /*
    * Writes a list of models in a file .dat
    * */
    void writeModel(List<Model> model) throws IOException;
    /*
    * reads a file .dat
    * returns a list of models
    * */
    List<Model> readModels() throws IOException;
}
