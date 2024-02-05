package com.maxencemacia.jsonWriter.service.impl;

import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.service.DAOService;
import com.maxencemacia.jsonWriter.service.PersistenceService;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.*;

@AllArgsConstructor
public class DAOServiceImpl implements DAOService {
    private final PersistenceService persistenceService = new PersistenceServiceImpl();
    @Override
    public List<Model> getAllModels() {
        try {
            return persistenceService.readModels();
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
        return List.of();
    }

    @Override
    public Optional<Model> getModelById(Long id) {
        try {
            return Optional.of(persistenceService.readModels().stream()
                    .filter(model -> Objects.equals(model.getId(), id))
                    .toList().get(0));
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Model> getModelByName(String name) {
        try {
            return Optional.of(persistenceService.readModels().stream()
                    .filter(model -> Objects.equals(model.getName(), name))
                    .toList().get(0));
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
        return Optional.empty();
    }

    @Override
    public void createModel(Model model) {
        try {
            List<Model> models = new ArrayList<>(
                    persistenceService.readModels().stream()
                    .sorted(Comparator.comparing(Model::getId))
                    .toList()
            );
            if (models.isEmpty()) {
                model.setId(1L);
            } else {
                Model lastModel = models.get(models.size() - 1);
                model.setId(lastModel.getId() + 1);
            }
            models.add(model);
            persistenceService.writeModel(models);
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
    }

    @Override
    public void updateModel(Model model) {
        try {
            List<Model> models = new ArrayList<>(
                    persistenceService.readModels().stream()
                    .filter(mod -> !Objects.equals(mod.getId(), model.getId()))
                    .toList()
            );
            models.add(model);
            models.stream()
                    .sorted(Comparator.comparing(Model::getId))
                    .toList();
            persistenceService.writeModel(models);
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
    }

    @Override
    public void deleteModel(Long id) {
        try {
            List<Model> models = persistenceService.readModels().stream()
                    .filter(mod -> !Objects.equals(mod.getId(), id))
                    .toList();
            persistenceService.writeModel(models);
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
    }
}
