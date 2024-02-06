package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.exception.AppException;
import com.maxencemacia.jsonWriter.exception.Error;
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
        try {
            Component[] modelButtons = view.getModelListContainer().getComponents();
            for (Component component : modelButtons) {
                if (component instanceof JButton button) {
                    button.removeActionListener(view.getDisplayModelController());
                }
            }

            String modelName = view.getModelNameInput().getText();
            if (modelName.equals("")) {
                throw new AppException(Error.MISSING_NAME);
            }

            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < view.getInputList().size(); i++) {
                if (i % 2 == 1)
                    continue;
                if (((TextField) view.getInputList().get(i)).getText().equals(""))
                    throw new AppException(Error.MISSING_ATTRIBUTE_NAME);
                if (((TextField) view.getInputList().get(i + 1)).getText().equalsIgnoreCase("S")) {
                    map.put(((TextField) view.getInputList().get(i)).getText(), "S");
                } else if (((TextField) view.getInputList().get(i + 1)).getText().equalsIgnoreCase("N")) {
                    map.put(((TextField) view.getInputList().get(i)).getText(), 0);
                } else
                    throw new AppException(Error.TYPE_ERROR);
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

            modelButtons = view.getModelListContainer().getComponents();
            for (Component component : modelButtons) {
                if (component instanceof JButton button) {
                    button.addActionListener(view.getDisplayModelController());
                }
            }

            this.view.getScreenContainer().updateUI();
            this.view.getWindow().pack();
            this.view.getWindow().setVisible(true);
        } catch(AppException ex) {
            System.out.println(ex);
            view.getGridLayoutAddModelForm().setRows(view.getGridLayoutAddModelForm().getRows() + 1);
            view.getScreenContainer().setLayout(view.getGridLayoutAddModelForm());
            if (ex.getError().equals(Error.MISSING_NAME)) {
                view.getScreenContainer().add(new JLabel("Veuillez entrer un nom du modèle"));
            } else if (ex.getError().equals(Error.MISSING_ATTRIBUTE_NAME)) {
                view.getScreenContainer().add(new JLabel("Veuillez entrer un nom d'attribut"));
            } else {
                view.getScreenContainer().add(new JLabel("Le type doit être S ou N"));
            }

            view.displayModelList();

            Component[] modelButtons = view.getModelListContainer().getComponents();
            for (Component component : modelButtons) {
                if (component instanceof JButton button) {
                    button.addActionListener(view.getDisplayModelController());
                }
            }
            view.getScreenContainer().updateUI();
            view.getWindow().pack();
            view.getWindow().setVisible(true);
        }
    }
}
