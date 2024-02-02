package com.maxencemacia.jsonWriter.service;

import com.maxencemacia.jsonWriter.model.Model;

import java.util.List;
import java.util.Optional;

public interface DAOService {
    /*
    * Get all models
    * */
    List<Model> getAllModels();
    /*
    * Get a model by id
    * */
    Optional<Model> getModelById(Long id);
    /*
     * Get a model by name
     * */
    Optional<Model> getModelByName(String name);
    /*
     * Creates a model
     * */
    void createModel(Model model);
    /*
     * Updates a model
     * */
    void updateModel(Model model);
    /*
     * Deletes a model
     * */
    void deleteModel(Long id);
}
