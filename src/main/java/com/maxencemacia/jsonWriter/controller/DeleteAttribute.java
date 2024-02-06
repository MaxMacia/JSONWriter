package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class DeleteAttribute implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getCurrentModel() != null) {
            JButton button = (JButton) e.getSource();
            Container inputContainer = button.getParent();
            if (inputContainer instanceof JPanel inputConainerPanel) {
                Component[] components = inputConainerPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof TextField field) {
                        view.getInputList().remove(field);
                    }
                }

                Container labelInputContainer = inputConainerPanel.getParent();
                if (labelInputContainer instanceof JPanel labelInputContainerPanel) {
                    view.getScreenContainer().remove(labelInputContainerPanel);
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
