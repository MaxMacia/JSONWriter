package com.maxencemacia.jsonWriter.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.service.JsonService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonServiceImpl implements JsonService {
    private final String DESTINATION = "target/data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void writeAsJson(Model model) {
        try {
            objectMapper.writeValue(new File(DESTINATION), model.getAttributes());
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
    }

    @Override
    public void writeAsJson(List<Model> models) {
        List<Map<String, Object>> attributes = models.stream()
                .map(Model::getAttributes)
                .toList();
        try {
            objectMapper.writeValue(new File(DESTINATION), attributes);
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
    }
}
