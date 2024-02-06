package com.maxencemacia.jsonWriter.service;

import com.maxencemacia.jsonWriter.model.Model;

import java.util.List;

public interface JSONService {
    /*
    * Writes an object as JSON object
    * */
    void writeAsJson(Model model);
    /*
     * Writes a list of objects as JSON array of objects
     * */
    void writeAsJson(List<Model> models);
}
