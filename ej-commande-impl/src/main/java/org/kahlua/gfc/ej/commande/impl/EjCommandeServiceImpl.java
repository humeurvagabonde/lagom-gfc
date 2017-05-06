/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package org.kahlua.gfc.ej.commande.impl;

import javax.inject.Inject;

import org.kahlua.gfc.ej.commande.api.CommandeData;
import org.kahlua.gfc.ej.commande.api.EjCommandeService;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import akka.NotUsed;

/**
 * Implementation of the EjCommandeService.
 */
public class EjCommandeServiceImpl implements EjCommandeService {

    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public EjCommandeServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        persistentEntityRegistry.register(EjCommandeEntity.class);
    }

    @Override
    public ServiceCall<CommandeData, NotUsed> creerCommande() {
        return request -> {
            System.out.println(request);
            return null;
        };

    }

    //    @Override
    //    public ServiceCall<NotUsed, String> hello(String id) {
    //        return request -> {
    //            // Look up the hello world entity for the given ID.
    //            PersistentEntityRef<EjCommandeCommand> ref = persistentEntityRegistry.refFor(HelloEntity.class, id);
    //            // Ask the entity the Hello command.
    //            return ref.ask(new Hello(id, Optional.empty()));
    //        };
    //    }
}
