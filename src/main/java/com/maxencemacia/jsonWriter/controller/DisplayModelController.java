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
        view.setCurrentModel(null);
        JButton button = (JButton) e.getSource();
        String name = button.getText();
        Model model = view.getDaoService().getModelByName(name).orElse(null);
        if (model != null) {
            view.setCurrentModel(model);
            Set<String> keySet = model.getAttributes().keySet();
            view.getScreenContainer().setLayout(new GridLayout(keySet.size() + 4, 1));
            view.getScreenContainer().add(new JLabel(model.getName()));
            view.getScreenContainer().add(new JLabel("{"));
            for (String key : keySet) {
                if (model.getAttributes().get(key).equals(0)) {
                    view.getScreenContainer().add(new JLabel("  " + key + " : Number"));
                } else {
                    view.getScreenContainer().add(new JLabel("  " + key + " : String"));
                }
            }
            view.getScreenContainer().add(new JLabel("}"));
            view.getScreenContainer().add(view.getUpdeleteButtonContainer());

            DisplayUpdateFormController displayUpdateFormController = new DisplayUpdateFormController(view);
            DeleteModelController deleteModelController = new DeleteModelController(view);
            view.getFirstUpdate().addActionListener(displayUpdateFormController);
            view.getDelete().addActionListener(deleteModelController);

        } else {
            view.getScreenContainer().add(new JLabel("Une erreur est survenu"));
        }
        view.getScreenContainer().updateUI();
        this.view.getWindow().setVisible(true);
        this.view.getWindow().pack();
    }
}
