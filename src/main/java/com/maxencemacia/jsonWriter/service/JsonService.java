package com.maxencemacia.jsonWriter.service;

import com.maxencemacia.jsonWriter.model.Model;

import java.util.List;

public interface JsonService {
    void writeAsJson(Model model);
    void writeAsJson(List<Model> models);
}
