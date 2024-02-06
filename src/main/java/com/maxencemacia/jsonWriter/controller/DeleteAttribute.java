package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@AllArgsConstructor
public class DeleteAttribute implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getCurrentModel() != null) {
            JButton button = (JButton) e.getSource();
            String buttonName = button.getName();
            JPanel fieldToRemove = view.getFieldList().stream()
                    .filter(field -> {
                        Component[] components = field.getComponents();
                        for (Component component : components) {
                            if (component instanceof JPanel container) {
                                Component[] containerComponents = container.getComponents();
                                for (Component containerComponent : containerComponents) {
                                    if (containerComponent instanceof JPanel containerChild) {
                                        Component[] containerChildren = containerChild.getComponents();
                                        for (Component input : containerChildren) {
                                            if (input instanceof JButton jButton) {
                                                return jButton.getName().equals(buttonName);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return true;
                    })
                    .findFirst()
                    .orElse(null);
            view.getScreenContainer().remove(fieldToRemove);

            view.getScreenContainer().updateUI();
            view.getWindow().pack();
            view.getWindow().setVisible(true);

        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
    }
}
