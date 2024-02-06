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
public class UpdateModelController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (view.getCurrentModel() != null) {
                view.getFieldList().removeAll(view.getFieldList());
                Component[] modelButtons = view.getModelListContainer().getComponents();
                for (Component component : modelButtons) {
                    if (component instanceof JButton button) {
                        button.removeActionListener(view.getDisplayModelController());
                    }
                }
                view.getSecondUpdate().removeActionListener(view.getUpdateModelController());
                String newModelName = view.getModelNameInput().getText();
                if (newModelName.equals("")) {
                    throw new AppException(Error.MISSING_NAME);
                }

                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < view.getInputList().size(); i++) {
                    if (i % 2 == 1)
                        continue;
                    if (view.getInputList().get(i).getText().equals(""))
                        throw new AppException(Error.MISSING_ATTRIBUTE_NAME);
                    if (view.getInputList().get(i + 1).getText().equals("S")) {
                        map.put(view.getInputList().get(i).getText(), "S");
                    } else if (view.getInputList().get(i + 1).getText().equals("N")) {
                        map.put(view.getInputList().get(i).getText(), 0);
                    } else
                        throw new AppException(Error.TYPE_ERROR);
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

                view.displayModelList();

                modelButtons = view.getModelListContainer().getComponents();
                for (Component component : modelButtons) {
                    if (component instanceof JButton button) {
                        button.addActionListener(view.getDisplayModelController());
                    }
                }

                view.getScreenContainer().updateUI();
                view.getWindow().pack();
                view.getWindow().setVisible(true);
            } else {
                view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
            }
        } catch (AppException ex) {
            System.out.println(ex);
            view.getGridLayoutUpdateModelForm().setRows(view.getGridLayoutUpdateModelForm().getRows() + 1);
            view.getScreenContainer().setLayout(view.getGridLayoutUpdateModelForm());
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
            view.getSecondUpdate().addActionListener(view.getUpdateModelController());

            view.getScreenContainer().updateUI();
            view.getWindow().pack();
            view.getWindow().setVisible(true);
        }
    }
}
