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
public class DisplayUpdateFormController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getCurrentModel() != null) {
            Set<String> keySet = view.getCurrentModel().getAttributes().keySet();
            view.getScreenContainer().removeAll();
            view.getHowManyAttributesInput().setText("");
            view.getModelNameInput().setText("");
            view.getScreenContainer().setLayout(new GridLayout(2 * (keySet.size() + 1) + 1, 1));
            view.getScreenContainer().add(view.getModelNameLabel());
            view.getModelNameInput().setText(view.getCurrentModel().getName());
            view.getScreenContainer().add(view.getModelNameInput());

            for (String key : keySet) {
                JPanel labelContainer = new JPanel();
                labelContainer.setLayout(new GridLayout(1, 2));
                labelContainer.add(new JLabel("Nom de l'attribut"));
                labelContainer.add(new JLabel("Type de l'attribut (S/N)"));

                JPanel inputContainer = new JPanel();
                inputContainer.setLayout(new GridLayout(1, 2));

                TextField attributeName = new TextField(key);
                TextField attributeType;

                if (view.getCurrentModel().getAttributes().get(key).equals("S")) {
                    attributeType = new TextField("S");
                } else {
                    attributeType = new TextField("N");
                }

                inputContainer.add(attributeName);
                inputContainer.add(attributeType);

                view.getInputList().add(attributeName);
                view.getInputList().add(attributeType);

                view.getScreenContainer().add(labelContainer);
                view.getScreenContainer().add(inputContainer);
            }
            UpdateModelController updateModelController = new UpdateModelController(view);
            view.getSecondUpdate().addActionListener(updateModelController);
            view.getScreenContainer().add(view.getSecondUpdate());
            this.view.getScreenContainer().updateUI();
            this.view.getWindow().pack();
            this.view.getWindow().setVisible(true);
        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
    }
}
