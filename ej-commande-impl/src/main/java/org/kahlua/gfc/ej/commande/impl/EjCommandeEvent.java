/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package org.kahlua.gfc.ej.commande.impl;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.Jsonable;

/**
 * This interface defines all the events that the EjCommande entity supports.
 * <p>
 * By convention, the events should be inner classes of the interface, which
 * makes it simple to get a complete picture of what events an entity has.
 */
public interface EjCommandeEvent extends Jsonable {

    /**
     * An event that represents a change in greeting message.
     */
    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class EjCommandeCreated implements EjCommandeEvent {
        public final String libelle;

        @JsonCreator
        public EjCommandeCreated(String libelle) {
            this.libelle = Preconditions.checkNotNull(libelle, "libelle");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof EjCommandeCreated && equalTo((EjCommandeCreated) another);
        }

        private boolean equalTo(EjCommandeCreated another) {
            return libelle.equals(another.libelle);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + libelle.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper("EjCommandeCreated").add("libelle", libelle).toString();
        }
    }
}
