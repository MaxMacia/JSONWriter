package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class DisplayWriteObjectChoiceOfModelController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        view.getScreenContainer().removeAll();
        Component[] modelButtons = view.getModelListContainer().getComponents();
        for (Component component : modelButtons) {
            if (component instanceof JButton button) {
                button.removeActionListener(view.getDisplayModelController());
            }
        }

        view.getScreenContainer().add(new JLabel("Veuillez choisir un modèle"));

        JButton button = (JButton) e.getSource();
        if (button.getText().equals("Ecrire un objet en JSON")) {
            modelButtons = view.getModelListContainer().getComponents();
            for (Component component : modelButtons) {
                if (component instanceof JButton jButton) {
                    jButton.addActionListener(view.getDisplayWriteObjectFormController());
                }
            }
        } else {
            modelButtons = view.getModelListContainer().getComponents();
            for (Component component : modelButtons) {
                if (component instanceof JButton jButton) {
                    jButton.addActionListener(view.getDisplayAskForNbOfObjectsController());
                }
            }
        }

        view.getScreenContainer().updateUI();
        view.getWindow().pack();
        view.getWindow().setVisible(true);
    }
}
