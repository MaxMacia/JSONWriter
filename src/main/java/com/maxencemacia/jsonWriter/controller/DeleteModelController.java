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
            view.getFirstUpdate().removeActionListener(view.getDisplayUpdateFormController());
            view.getDelete().removeActionListener(view.getDeleteModelController());
            view.getDaoService().deleteModel(view.getCurrentModel().getId());
            Component[] modelButtons = view.getModelListContainer().getComponents();
            for (Component component : modelButtons) {
                if (component instanceof JButton button) {
                    button.removeActionListener(view.getDisplayModelController());
                }
            }
            view.getScreenContainer().removeAll();

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
    }
}
