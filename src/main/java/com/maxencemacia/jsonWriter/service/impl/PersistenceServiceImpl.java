package com.maxencemacia.jsonWriter.service.impl;

import com.maxencemacia.jsonWriter.model.Model;
import com.maxencemacia.jsonWriter.service.PersistenceService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceServiceImpl implements PersistenceService {
    private final String DESTINATION = "target/models.dat";
    @Override
    public void writeModel(List<Model> models) throws IOException {
        ObjectOutputStream out = null;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(DESTINATION);
            out = new ObjectOutputStream(fos);

            for (Model model : models) {
                out.writeObject(model);
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        } finally {
            if (out != null) out.close();
            System.out.println("Ecriture de " + models.size() + " models " + " dans:\t" + DESTINATION);
        }
    }

    @Override
    public List<Model> readModels() throws IOException {
        System.out.println("Lecture de:\t" + DESTINATION);
        List<Model> models = new ArrayList<>();
        ObjectInputStream in = null;
        FileInputStream fis;
        try {
            fis = new FileInputStream(DESTINATION);
            in = new ObjectInputStream(fis);
            Object object = null;

            while((object = in.readObject()) != null) {
                models.add((Model) object);
            }
        }catch (EOFException e) {
            System.out.println("Fin du fichier");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Exception : " + e);
        } finally {
            if (in != null) in.close();
            System.out.println("Fin de lecture des models de:\t" + DESTINATION);
        }
        return models;
    }
}
