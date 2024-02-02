package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class UpdateModelController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getCurrentModel() != null) {
            String newModelName = view.getModelNameInput().getText();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < view.getInputList().size(); i++) {
                if (i % 2 == 1)
                    continue;
                if (view.getInputList().get(i + 1).getText().equals("S")) {
                    map.put(view.getInputList().get(i).getText(), "S");
                } else if (view.getInputList().get(i + 1).getText().equals("N")) {
                    map.put(view.getInputList().get(i).getText(), 0);
                }
            }
            view.getInputList().removeAll(view.getInputList());

            view.getDaoService().updateModel(
                    Model.builder()
                            .id(view.getCurrentModel().getId())
                            .name(newModelName)
                            .attributes(map)
                            .build()
            );

            view.getScreenContainer().removeAll();
            view.getModelNameInput().setText("");

            this.view.getScreenContainer().updateUI();
            this.view.getWindow().pack();
            this.view.getWindow().setVisible(true);
        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
    }
}
