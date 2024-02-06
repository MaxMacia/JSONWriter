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
public class WriteObjectController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (view.getCurrentModel() != null) {
                Component[] modelButtons = view.getModelListContainer().getComponents();
                for (Component component : modelButtons) {
                    if (component instanceof JButton button) {
                        button.removeActionListener(view.getDisplayModelController());
                    }
                }
                Model model = view.getCurrentModel();
                Map<String, Object> map = new HashMap<>();

                for (int i = 0; i < view.getInputList().size(); i++) {
                    if (i % 2 == 0)
                        continue;
                    if (((TextField) view.getInputList().get(i)).getText().equals(""))
                        throw new AppException(Error.MISSING_VALUE);
                    if (model.getAttributes().get(view.getInputList().get(i).getName()).equals(0)) {
                        map.put(view.getInputList().get(i).getName(), Integer.parseInt(((TextField) view.getInputList().get(i)).getText()));
                    } else {
                        map.put(view.getInputList().get(i).getName(), ((TextField) view.getInputList().get(i)).getText());
                    }
                }
                view.getInputList().removeAll(view.getInputList());

                model.setAttributes(map);

                view.getJsonService().writeAsJson(model);

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
            view.getGridLayoutWriteObjectForm().setRows(view.getGridLayoutWriteObjectForm().getRows() + 1);
            view.getScreenContainer().setLayout(view.getGridLayoutWriteObjectForm());

            view.getScreenContainer().add(new JLabel("Veuillez entrer une valeur pour l'attribut"));

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
