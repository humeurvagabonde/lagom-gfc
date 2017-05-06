/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package org.kahlua.gfc.ej.commande.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.namedCall;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import akka.NotUsed;

/**
 * EJ COmmande service interface.
 */
public interface EjCommandeService extends Service {

    ServiceCall<CommandeData, NotUsed> creerCommande();

    @Override
    default Descriptor descriptor() {
        return named("ejcommandeservice").withCalls(
                namedCall("/api/commandes", this::creerCommande)).withAutoAcl(true);
    }
}
