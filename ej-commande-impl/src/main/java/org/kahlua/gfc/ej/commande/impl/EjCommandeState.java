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
import com.lightbend.lagom.serialization.CompressedJsonable;

/**
 * The state for the {@link Hello} entity.
 */
@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public final class EjCommandeState implements CompressedJsonable {

    public final String libelle;

    @JsonCreator
    public EjCommandeState(String libelle) {
        this.libelle = Preconditions.checkNotNull(libelle, "libelle");
    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof EjCommandeState && equalTo((EjCommandeState) another);
    }

    private boolean equalTo(EjCommandeState another) {
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
        return MoreObjects.toStringHelper("EjCommandeState").add("libelle", libelle).toString();
    }
}
