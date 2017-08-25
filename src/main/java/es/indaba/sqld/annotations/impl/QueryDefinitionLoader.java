package es.indaba.sqld.annotations.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import es.indaba.sqld.QueryDefinitionsHolder;
import es.indaba.sqld.loader.SQLDClassPathLoader;

@ApplicationScoped
public class QueryDefinitionLoader {
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        SQLDClassPathLoader.loadSqlds("");
    }
 
    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        QueryDefinitionsHolder.clear(); 
    }
}