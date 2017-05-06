/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package org.kahlua.gfc.ej.commande.impl;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.kahlua.gfc.ej.commande.impl.EjCommandeCommand.CreerCommande;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;

import akka.Done;

/**
 * This interface defines all the commands that the Hello entity supports.
 * 
 * By convention, the commands should be inner classes of the interface, which
 * makes it simple to get a complete picture of what commands an entity
 * supports.
 */
public interface EjCommandeCommand extends Jsonable {

    /**
     * A command to switch the greeting message.
     * <p>
     * It has a reply type of {@link akka.Done}, which is sent back to the caller
     * when all the events emitted by this command are successfully persisted.
     */
    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class CreerCommande implements EjCommandeCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
        public final String message;

        @JsonCreator
        public CreerCommande(String message) {
            this.message = Preconditions.checkNotNull(message, "message");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof CreerCommande && equalTo((CreerCommande) another);
        }

        private boolean equalTo(CreerCommande another) {
            return message.equals(another.message);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + message.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper("UseGreetingMessage").add("message", message).toString();
        }
    }
}
