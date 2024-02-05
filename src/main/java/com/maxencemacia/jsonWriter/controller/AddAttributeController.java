package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class AddAttributeController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getCurrentModel() != null) {
            view.getGridLayoutUpdateModelForm().setRows(view.getGridLayoutUpdateModelForm().getRows() + 2);
            view.getScreenContainer().setLayout(view.getGridLayoutUpdateModelForm());
            view.getScreenContainer().remove(view.getUpaddButtonContainer());

            JPanel labelContainer = new JPanel();
            labelContainer.setLayout(new GridLayout(1, 2));
            labelContainer.add(new JLabel("Nom de l'attribut"));
            labelContainer.add(new JLabel("Type de l'attribut (S/N)"));

            JPanel inputContainer = new JPanel();
            inputContainer.setLayout(new GridLayout(1, 3));

            TextField attributeName = new TextField();
            TextField attributeType = new TextField();

            inputContainer.add(attributeName);
            inputContainer.add(attributeType);
            inputContainer.add(new JButton("supprimer"));

            view.getInputList().add(attributeName);
            view.getInputList().add(attributeType);

            view.getScreenContainer().add(labelContainer);
            view.getScreenContainer().add(inputContainer);

            view.getScreenContainer().add(view.getUpaddButtonContainer());

            view.getScreenContainer().updateUI();
            view.getWindow().pack();
            view.getWindow().setVisible(true);
        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
    }
}
