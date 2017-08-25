package es.indaba.sqld.annotations.impl;

import java.lang.annotation.Annotation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.deltaspike.core.util.BeanUtils;

import es.indaba.sqld.QueryDefinition;
import es.indaba.sqld.QueryDefinitionsHolder;
import es.indaba.sqld.annotations.api.QueryResolver;

@ApplicationScoped
public class QueryDefinitionProducer {

    @Produces
    @Dependent
    @QueryResolver(name = "ignore")
    public String produceQueryString(InjectionPoint injectionPoint) {
        QueryResolver queryResolver = getAnnotation(injectionPoint, QueryResolver.class);

        if (queryResolver == null) {
            throw new IllegalStateException("producer method called without @QueryResolver being present!");
        }

        return QueryDefinitionsHolder.getQueryAsString(queryResolver.name());

    }

    @Produces
    @Dependent
    @QueryResolver(name = "ignore")
    public QueryDefinition produceQueryDefinition(InjectionPoint injectionPoint) {
        QueryResolver queryResolver = getAnnotation(injectionPoint, QueryResolver.class);

        if (queryResolver == null) {
            throw new IllegalStateException("producer method called without @QueryResolver being present!");
        }

        return new QueryDefinition(queryResolver.name());
    }

    /**
     * @param injectionPoint current injection point
     * @param targetType target type
     * @param <T> type
     * @return annotation instance extracted from the injection point which matches the given type
     */
    protected <T extends Annotation> T getAnnotation(InjectionPoint injectionPoint, Class<T> targetType) {
        return BeanUtils.extractAnnotation(injectionPoint.getAnnotated(), targetType);
    }


}
