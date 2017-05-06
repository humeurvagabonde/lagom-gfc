/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package org.kahlua.gfc.ej.commande.impl;

import java.util.Optional;

import org.kahlua.gfc.ej.commande.impl.EjCommandeCommand.CreerCommande;
import org.kahlua.gfc.ej.commande.impl.EjCommandeEvent.EjCommandeCreated;

import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity.Behavior;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity.BehaviorBuilder;

import akka.Done;

/**
 * This is an event sourced entity. It has a state, {@link EjCommandeState}, which
 * stores what the greeting should be (eg, "Hello").
 * <p>
 * Event sourced entities are interacted with by sending them commands. This
 * entity supports two commands, a {@link CreerCommande} command, which is
 * used to change the greeting, and a {@link Hello} command, which is a read
 * only command which returns a greeting to the name specified by the command.
 * <p>
 * Commands get translated to events, and it's the events that get persisted by
 * the entity. Each event will have an event handler registered for it, and an
 * event handler simply applies an event to the current state. This will be done
 * when the event is first created, and it will also be done when the entity is
 * loaded from the database - each event will be replayed to recreate the state
 * of the entity.
 * <p>
 * This entity defines one event, the {@link EjCommandeCreated} event,
 * which is emitted when a {@link CreerCommande} command is received.
 */
public class EjCommandeEntity extends PersistentEntity<EjCommandeCommand, EjCommandeEvent, EjCommandeState> {

    /**
     * An entity can define different behaviours for different states, but it will
     * always start with an initial behaviour. This entity only has one behaviour.
     */
    @Override
    public Behavior initialBehavior(Optional<EjCommandeState> snapshotState) {

        /*
         * Behaviour is defined using a behaviour builder. The behaviour builder
         * starts with a state, if this entity supports snapshotting (an
         * optimisation that allows the state itself to be persisted to combine many
         * events into one), then the passed in snapshotState may have a value that
         * can be used.
         *
         * Otherwise, the default state is to use the Hello greeting.
         */
        BehaviorBuilder b = newBehaviorBuilder(
                snapshotState.orElse(new EjCommandeState("Libelle Ej Commande")));

        /*
         * Command handler for the UseGreetingMessage command.
         */
        b.setCommandHandler(CreerCommande.class, (cmd, ctx) ->
        // In response to this command, we want to first persist it as a
        // GreetingMessageChanged event
        ctx.thenPersist(new EjCommandeCreated(cmd.message),
                // Then once the event is successfully persisted, we respond with done.
                evt -> ctx.reply(Done.getInstance())));

        /*
         * Event handler for the GreetingMessageChanged event.
         */
        b.setEventHandler(EjCommandeCreated.class,
                // We simply update the current state to use the greeting message from the event.
                evt -> new EjCommandeState(evt.libelle));

        /*
         * We've defined all our behaviour, so build and return it.
         */
        return b.build();
    }

}
