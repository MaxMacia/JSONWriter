package com.maxencemacia.jsonWriter.view;

import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.service.DAOService;
import com.maxencemacia.jsonWriter.service.impl.DAOServiceImpl;

import java.util.*;

public class OldView {
    private final Scanner scanner = new Scanner(System.in);
    private final DAOService daoService = new DAOServiceImpl();
    public void mainLoop() {
        boolean bool = true;

        while(bool) {
            String reply = getFirstUserInput();

            switch (reply) {
                case "1":
                    displayAllModels();
                    System.out.println();
                    mainLoop();
                    break;
                case "2":
                    createAModel();
                    System.out.println();
                    mainLoop();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    System.out.println();
                    System.out.println("Je n'ai pas compris votre réponse");
                    System.out.println();
                    mainLoop();
                    break;
            }
            bool = false;
        }
        scanner.close();
    }

    private void createAModel() {
        System.out.println();
        System.out.println("Veuillez renseigner un nom");
        String name = scanner.next();

        Model model = Model.builder()
                .name(name)
                .attributes(new HashMap<>())
                .build();

        System.out.println();
        System.out.println("Veuillez renseigner un nombre d'attributs");
        int size = scanner.nextInt();

        for (int i = 0; i < size; i++) {
            createEachAttribute(i, model);
        }
        daoService.createModel(model);
    }

    private void createEachAttribute(int i, Model model) {
        String key = getAttributeKey(i);
        String reply = getAttributeType();

        switch (reply) {
            case "S":
                model.getAttributes().put(key, "S");
                break;
            case "N":
                model.getAttributes().put(key, 0);
                break;
            default:
                createEachAttribute(i, model);
                break;
        }
    }

    private String getAttributeType() {
        System.out.println();
        System.out.println("Veuillez entrer S si l'attribut est une chaine de caractère ou N si l'attribut est un nombre");
        return scanner.next();
    }

    private String getAttributeKey(int i) {
        System.out.println();
        System.out.println("Veuillez renseigner la clée de l'attribut n° " + (i + 1));
        return scanner.next();
    }

    private void displayAllModels() {
        List<Model> models = daoService.getAllModels();
        for(Model model : models) {
            System.out.println(model.getId() + " - " + model.getName());
        }
        chooseAModel();
    }

    private void chooseAModel() {
        System.out.println("Choisissez un modèle ou tapez Q pour retourner au menu");
        String reply = scanner.next();

        if(!reply.equalsIgnoreCase("Q")) {
            Long index = Long.parseLong(reply);
            Model model = daoService.getModelById(index).orElse(null);
            assert model != null;
            displayAModel(model);
        }
    }

    private void displayAModel(Model model) {
        System.out.println();
        System.out.println("Nom : " + model.getName());
        System.out.println();
        Set<String> keySet = model.getAttributes().keySet();

        for (String key : keySet) {
            if (model.getAttributes().get(key).equals(0)) {
                System.out.println(key + " : Number");
            } else {
                System.out.println(key + " : String");
            }
        }
        exitingDisplayModel();
    }

    private void exitingDisplayModel() {
        System.out.println("Tapez R pour revenir aux modèles ou Q pour retourner au menu");
        String reply = scanner.next();

        switch (reply) {
            case "R":
                displayAllModels();
                break;
            case "Q":
                break;
            default:
                System.out.println();
                System.out.println("Je n'ai pas compris votre réponse");
                System.out.println();
                exitingDisplayModel();
                break;
        }
    }

    private String getFirstUserInput() {
        System.out.println("Que souhaitez-vous faire?");
        System.out.println("1 - consulter les modèles");
        System.out.println("2 - créer un modèles");
        System.out.println("3 - écrire un objet JSON");
        System.out.println("4 - écrire une liste d'objets JSON");
        System.out.println("5 - quitter l'application");

        return scanner.next();
    }
}
