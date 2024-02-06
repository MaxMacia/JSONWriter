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
public class DisplayWriteObjectFormController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        view.getInputList().removeAll(view.getInputList());
        Component[] modelButtons = view.getModelListContainer().getComponents();
        for (Component component : modelButtons) {
            if (component instanceof JButton button) {
                button.removeActionListener(view.getDisplayWriteObjectFormController());
            }
        }
        modelButtons = view.getModelListContainer().getComponents();
        for (Component component : modelButtons) {
            if (component instanceof JButton button) {
                button.addActionListener(view.getDisplayModelController());
            }
        }
        view.getScreenContainer().removeAll();
        view.getWrite().removeActionListener(view.getWriteObjectController());
        view.setCurrentModel(null);
        JButton button = (JButton) e.getSource();
        String name = button.getText();
        Model model = view.getDaoService().getModelByName(name).orElse(null);

        if (model != null) {
            view.setCurrentModel(model);
            Set<String> keySet = model.getAttributes().keySet();
            view.getGridLayoutWriteObjectForm().setRows(keySet.size() + 2);
            view.getScreenContainer().setLayout(view.getGridLayoutWriteObjectForm());
            view.getScreenContainer().add(new JLabel(model.getName()));

            for (String key : keySet) {
                JPanel inputContainer = new JPanel();
                JLabel attributeName = new JLabel(key + " : ");
                TextField attributeValue = new TextField(12);
                attributeValue.setName(key);
                inputContainer.add(attributeName);
                inputContainer.add(attributeValue);

                view.getInputList().add(attributeName);
                view.getInputList().add(attributeValue);

                view.getScreenContainer().add(inputContainer);
            }

            view.getWrite().addActionListener(view.getWriteObjectController());
            view.getScreenContainer().add(view.getWrite());

            view.getScreenContainer().updateUI();
            view.getWindow().pack();
            view.getWindow().setVisible(true);
        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
    }
}
