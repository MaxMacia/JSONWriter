package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class DeleteModelController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getCurrentModel() != null) {
            view.getDaoService().deleteModel(view.getCurrentModel().getId());
            view.getScreenContainer().removeAll();

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
        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
    }
}
