package org.kahlua.gfc.ej.commande.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//Immutable / lombok
@JsonDeserialize
public class CommandeData {

    public String libelle;

    public CommandeData(String libelle) {
        this.libelle = libelle;
    }

}
