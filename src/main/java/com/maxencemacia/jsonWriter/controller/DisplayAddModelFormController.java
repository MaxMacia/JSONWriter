package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class DisplayAddModelFormController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        String attributesNbStr = view.getHowManyAttributesInput().getText();
        try {
            int attributesNb = Integer.parseInt(attributesNbStr);
            view.getScreenContainer().removeAll();
            view.getHowManyAttributesInput().setText("");
            view.getModelNameInput().setText("");
            view.getScreenContainer().setLayout(new GridLayout(2 * (attributesNb + 1) + 1, 1));
            view.getScreenContainer().add(view.getModelNameLabel());
            view.getScreenContainer().add(view.getModelNameInput());
            for (int i = 0; i < attributesNb; i++) {
                JPanel labelContainer = new JPanel();
                labelContainer.setLayout(new GridLayout(1, 2));
                labelContainer.add(new JLabel("Nom de l'attribut"));
                labelContainer.add(new JLabel("Type de l'attribut (S/N)"));

                JPanel inputContainer = new JPanel();
                inputContainer.setLayout(new GridLayout(1, 2));

                TextField attributeName = new TextField();
                TextField attributeType = new TextField();

                inputContainer.add(attributeName);
                inputContainer.add(attributeType);

                view.getInputList().add(attributeName);
                view.getInputList().add(attributeType);

                view.getScreenContainer().add(labelContainer);
                view.getScreenContainer().add(inputContainer);
            }
            view.getScreenContainer().add(view.getValidateAttrbutes());
            this.view.getScreenContainer().updateUI();
            this.view.getWindow().pack();
            this.view.getWindow().setVisible(true);
        } catch(Exception ex) {
            System.out.println(ex);
            view.getHowManyAttributesInput().setText("");
            if (view.getGridLayoutAttributeNb().getRows() < 4) {
                view.getGridLayoutAttributeNb().setRows(view.getGridLayoutAttributeNb().getRows() + 1);
                view.getScreenContainer().setLayout(view.getGridLayoutAttributeNb());
                view.getScreenContainer().add(new JLabel("Veuillez entrer un nombre"));
                this.view.getScreenContainer().updateUI();
                this.view.getWindow().pack();
                this.view.getWindow().setVisible(true);
            }
        }
    }
}
