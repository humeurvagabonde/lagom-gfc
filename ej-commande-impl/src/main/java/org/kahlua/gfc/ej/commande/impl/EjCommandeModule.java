/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package org.kahlua.gfc.ej.commande.impl;

import org.kahlua.gfc.ej.commande.api.EjCommandeService;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * The module that binds the HelloService so that it can be served.
 */
public class EjCommandeModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(serviceBinding(EjCommandeService.class, EjCommandeServiceImpl.class));
    }
}
