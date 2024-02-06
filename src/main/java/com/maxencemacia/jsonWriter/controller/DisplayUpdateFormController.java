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
            view.getFirstUpdate().removeActionListener(view.getDisplayUpdateFormController());
            view.getDelete().removeActionListener(view.getDeleteModelController());
            view.getSecondUpdate().removeActionListener(view.getUpdateModelController());
            view.getScreenContainer().removeAll();
            view.getHowManyAttributesInput().setText("");
            view.getModelNameInput().setText("");
            view.getGridLayoutUpdateModelForm().setRows(2 * (keySet.size() + 1) + 1);
            view.getScreenContainer().setLayout(view.getGridLayoutUpdateModelForm());
            view.getScreenContainer().add(view.getModelNameLabel());
            view.getModelNameInput().setText(view.getCurrentModel().getName());
            view.getScreenContainer().add(view.getModelNameInput());
            view.getInputList().removeAll(view.getInputList());

            for (String key : keySet) {
                JPanel labelContainer = new JPanel();
                labelContainer.setLayout(new GridLayout(1, 2));
                labelContainer.add(new JLabel("Nom de l'attribut"));
                labelContainer.add(new JLabel("Type de l'attribut (S/N)"));

                JPanel inputContainer = new JPanel();
                inputContainer.setLayout(new GridLayout(1, 3));

                TextField attributeName = new TextField(key);
                TextField attributeType;

                if (view.getCurrentModel().getAttributes().get(key).equals("S")) {
                    attributeType = new TextField("S");
                } else {
                    attributeType = new TextField("N");
                }

                inputContainer.add(attributeName);
                inputContainer.add(attributeType);
                JButton button = new JButton("supprimer");
                button.addActionListener(view.getDeleteAttribute());
                inputContainer.add(button);

                view.getInputList().add(attributeName);
                view.getInputList().add(attributeType);

                JPanel labelInputContainer = new JPanel();
                labelInputContainer.setLayout(new GridLayout(2, 1));
                labelInputContainer.add(labelContainer);
                labelInputContainer.add(inputContainer);

                view.getScreenContainer().add(labelInputContainer);
            }

            view.getSecondUpdate().addActionListener(view.getUpdateModelController());
            view.getAddAttribute().addActionListener(view.getAddAttributeController());
            view.getScreenContainer().add(view.getUpaddButtonContainer());
            view.getScreenContainer().updateUI();
            view.getWindow().pack();
            view.getWindow().setVisible(true);
        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
    }
}
