package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

@AllArgsConstructor
public class DisplayModelController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        view.getScreenContainer().removeAll();
        JButton button = (JButton) e.getSource();
        String name = button.getText();
        Model model = view.getDaoService().getModelByName(name).orElse(null);
        if (model != null) {
            Set<String> keySet = model.getAttributes().keySet();
            view.getScreenContainer().setLayout(new GridLayout(keySet.size(), 1));
            for (String key : keySet) {
                if (model.getAttributes().get(key).equals(0)) {
                    view.getScreenContainer().add(new JLabel(key + " : Number"));
                } else {
                    view.getScreenContainer().add(new JLabel(key + " : String"));
                }
            }
            view.getScreenContainer().updateUI();
            this.view.getWindow().setVisible(true);
            this.view.getWindow().pack();
        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
            view.getScreenContainer().updateUI();
            this.view.getWindow().setVisible(true);
            this.view.getWindow().pack();
        }
    }
}
