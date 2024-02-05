package com.maxencemacia.jsonWriter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    MISSING_NAME("Le champs nom du modèle doit être renseigné"),
    MISSING_ATTRIBUTE_NAME("Le champs nom de l'attribut doit être renseigné"),
    TYPE_ERROR("Le type doit être string ou number");

    private final String message;
}
