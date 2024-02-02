package com.maxencemacia.jsonWriter.controller;

import com.maxencemacia.jsonWriter.view.View;
import lombok.AllArgsConstructor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class DisplayAskFoAttributesNbFormController implements ActionListener {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        view.getScreenContainer().removeAll();
        view.getHowManyAttributesInput().setText("");
        view.getScreenContainer().setLayout(new GridLayout(3, 1));
        view.getScreenContainer().add(view.getHowManyAttributesLabel());
        view.getScreenContainer().add(view.getHowManyAttributesInput());
        view.getScreenContainer().add(view.getValidateAttributeNb());
        this.view.getScreenContainer().updateUI();
        this.view.getWindow().pack();
        this.view.getWindow().setVisible(true);
    }
}
