package com.maxencemacia.jsonWriter.view;

import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.service.DAOService;
import com.maxencemacia.jsonWriter.service.impl.DAOServiceImpl;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class View {
    private JFrame window;
    private JPanel modelListContainer, screenContainer, buttonsContainer;
    private JLabel howManyAttributesLabel;
    private TextField howManyAttributesInput;
    private JButton addModel, writeObject, writeListOfObject, validateAttributeNb;
    private final DAOService daoService = new DAOServiceImpl();
    public View() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screenSize.width;
        int h = screenSize.height;

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(1, 3));
        window.setTitle("JSONWriter");
        window.setSize(w/4, h/2);

        modelListContainer = new JPanel();
        screenContainer = new JPanel();
        buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new GridLayout(3, 1));

        addModel = new JButton("Ajouter un modèle");
        writeObject = new JButton("Ecrire un objet en JSON");
        writeListOfObject = new JButton("Ecrire une liste d'objet en JSON");
        validateAttributeNb = new JButton("Valider");

        howManyAttributesLabel = new JLabel("Combien d'attributs?");
        howManyAttributesInput = new TextField(12);

        buttonsContainer.add(addModel);
        buttonsContainer.add(writeObject);
        buttonsContainer.add(writeListOfObject);

        displayModelList();

        window.getContentPane().add(modelListContainer);
        window.getContentPane().add(screenContainer);
        window.getContentPane().add(buttonsContainer);

        this.window.setVisible(true);
        this.window.pack();
        this.window.show();
    }

    private void displayModelList() {
        List<Model> models = daoService.getAllModels();
        modelListContainer.removeAll();
        modelListContainer.setLayout(new GridLayout(models.size(), 1));

        for (Model model : models) {
            JButton button = new JButton(model.getName());
            modelListContainer.add(button);
        }

    }
}
