package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class AddModelController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        String modelName = view.getModelNameInput().getText();
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

        view.getDaoService().createModel(
                Model.builder()
                        .name(modelName)
                        .attributes(map)
                        .build()
        );

        view.getScreenContainer().removeAll();
        view.getModelNameInput().setText("");

        view.displayModelList();
        DisplayModelController displayModelController = new DisplayModelController(view);
        Component[] modelButtons = view.getModelListContainer().getComponents();
        for (Component component : modelButtons) {
            if (component instanceof JButton button) {
                button.addActionListener(displayModelController);
            }
        }

        this.view.getScreenContainer().updateUI();
        this.view.getWindow().pack();
        this.view.getWindow().setVisible(true);
    }
}
