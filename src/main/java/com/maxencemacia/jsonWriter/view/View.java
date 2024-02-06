package com.maxencemacia.jsonWriter.view;

import com.maxencemacia.jsonWriter.controller.*;
import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.service.DAOService;
import com.maxencemacia.jsonWriter.service.impl.DAOServiceImpl;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class View {
    private Model currentModel;
    private JFrame window;
    private JPanel modelListContainer, screenContainer, buttonsContainer, updeleteButtonContainer, upaddButtonContainer;
    private List<JPanel> fieldList;
    private JLabel howManyAttributesLabel, modelNameLabel;
    private TextField howManyAttributesInput, modelNameInput;
    private List<TextField> inputList;
    private JButton addModel, writeObject, writeListOfObject, validateAttributeNb, validateAttrbutes, firstUpdate, secondUpdate, delete, addAttribute;
    private GridLayout gridLayoutAttributeNb, gridLayoutAddModelForm, gridLayoutUpdateModelForm;
    private ActionListener displayModelController, displayAskFoAttributesNbFormController, displayAddModelFormController, addModelController, displayUpdateFormController, deleteModelController, updateModelController, addAttributeController, deleteAttribute;
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
        updeleteButtonContainer = new JPanel();
        upaddButtonContainer = new JPanel();

        displayModelController= new DisplayModelController(this);
        displayAskFoAttributesNbFormController = new DisplayAskFoAttributesNbFormController(this);
        displayAddModelFormController = new DisplayAddModelFormController(this);
        addModelController = new AddModelController(this);
        displayUpdateFormController = new DisplayUpdateFormController(this);
        deleteModelController = new DeleteModelController(this);
        updateModelController = new UpdateModelController(this);
        addAttributeController = new AddAttributeController(this);
        deleteAttribute = new DeleteAttribute(this);

        addModel = new JButton("Ajouter un modèle");
        writeObject = new JButton("Ecrire un objet en JSON");
        writeListOfObject = new JButton("Ecrire une liste d'objet en JSON");
        validateAttributeNb = new JButton("Valider");
        validateAttrbutes = new JButton("Valider");
        firstUpdate = new JButton("éditer");
        secondUpdate = new JButton("éditer");
        delete = new JButton("supprimer");
        addAttribute = new JButton("Ajouter un attribut");

        howManyAttributesLabel = new JLabel("Combien d'attributs?");
        howManyAttributesInput = new TextField(12);

        modelNameLabel = new JLabel("Nom du modèle :");
        modelNameInput = new TextField(12);

        inputList = new ArrayList<>();
        fieldList = new ArrayList<>();

        gridLayoutAttributeNb = new GridLayout(3, 1);
        gridLayoutAddModelForm = new GridLayout(3, 1);
        gridLayoutUpdateModelForm = new GridLayout(3, 1);

        updeleteButtonContainer.add(firstUpdate);
        updeleteButtonContainer.add(delete);

        upaddButtonContainer.add(secondUpdate);
        upaddButtonContainer.add(addAttribute);

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

    public void displayModelList() {
        List<Model> models = daoService.getAllModels();
        modelListContainer.removeAll();
        modelListContainer.setLayout(new GridLayout(models.size(), 1));

        for (Model model : models) {
            JButton button = new JButton(model.getName());
            modelListContainer.add(button);
        }

    }
}
