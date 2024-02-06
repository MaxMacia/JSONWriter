package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class DisplayAskForNbOfObjectsController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] modelButtons = view.getModelListContainer().getComponents();
        for (Component component : modelButtons) {
            if (component instanceof JButton button) {
                button.removeActionListener(view.getDisplayWriteObjectFormController());
            }
        }
        modelButtons = view.getModelListContainer().getComponents();
        for (Component component : modelButtons) {
            if (component instanceof JButton button) {
                button.addActionListener(view.getDisplayModelController());
            }
        }
        JButton button = (JButton) e.getSource();
        String name = button.getText();
        Model model = view.getDaoService().getModelByName(name).orElse(null);
        if (model != null) {
            view.getScreenContainer().removeAll();
            view.setCurrentModel(model);
            view.getHowManyAttributesInput().setText("");
            view.getGridLayoutObjNb().setRows(3);
            view.getScreenContainer().setLayout(view.getGridLayoutObjNb());
            view.getScreenContainer().add(view.getHowManyObjLabel());
            view.getScreenContainer().add(view.getHowManyObjInput());
            view.getScreenContainer().add(view.getValidateNbObj());
            view.getScreenContainer().updateUI();
            view.getWindow().pack();
            view.getWindow().setVisible(true);
        }


    }
}
