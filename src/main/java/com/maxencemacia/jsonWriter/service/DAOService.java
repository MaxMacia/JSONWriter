package com.maxencemacia.jsonWriter.service;

import com.maxencemacia.jsonWriter.model.Model;

import java.util.List;
import java.util.Optional;

public interface DAOService {
    List<Model> getAllModels();
    Optional<Model> getModelById(Long id);
    Optional<Model> getModelByName(String name);
    void createModel(Model model);
    void updateModel(Model model);
    void deleteModel(Long id);
}
