package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

@AllArgsConstructor
public class DisplayWriteListOfObjectsFormController implements ActionListener {
    private View view;
    private int n = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getCurrentModel() != null) {
            String objNbStr = view.getHowManyObjInput().getText();
            int objNb = Integer.parseInt(objNbStr);
            Set<String> keySet = view.getCurrentModel().getAttributes().keySet();

            while (n <= objNb) {
                view.getHowManyObjInput().setText("");
                view.getInputList().removeAll(view.getInputList());
                view.getScreenContainer().removeAll();
                view.getGridLayoutWriteObjectForm().setRows(keySet.size() + 2);
                view.getScreenContainer().setLayout(view.getGridLayoutWriteObjectForm());
                view.getScreenContainer().add(new JLabel(view.getCurrentModel().getName()));

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

                view.getScreenContainer().updateUI();
                view.getWindow().pack();
                view.getWindow().setVisible(true);

                if (n == objNb) {
                    view.getScreenContainer().add(view.getNextObj());
                } else {
                    view.getWrite().addActionListener(
                            e1 -> n++
                    );
                    view.getScreenContainer().add(view.getWrite());
                }
            }
        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
    }
}
