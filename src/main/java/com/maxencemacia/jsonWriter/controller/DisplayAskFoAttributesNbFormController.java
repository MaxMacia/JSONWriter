package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class DisplayAskFoAttributesNbFormController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        view.getScreenContainer().removeAll();
        view.getHowManyAttributesInput().setText("");
        view.getGridLayoutAttributeNb().setRows(3);
        view.getScreenContainer().setLayout(view.getGridLayoutAttributeNb());
        view.getScreenContainer().add(view.getHowManyAttributesLabel());
        view.getScreenContainer().add(view.getHowManyAttributesInput());
        view.getScreenContainer().add(view.getValidateAttributeNb());
        view.getScreenContainer().updateUI();
        view.getWindow().pack();
        view.getWindow().setVisible(true);
    }
}
