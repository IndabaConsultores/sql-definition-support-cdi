package es.indaba.sqld.annotations.cdi.extension;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.indaba.sqld.QueryDefinitionsHolder;
import es.indaba.sqld.annotations.cdi.api.QueryRepositories;
import es.indaba.sqld.annotations.cdi.api.QueryRepository;
import es.indaba.sqld.loader.SQLDClassPathLoader;

public class SQLDCDIExtension implements javax.enterprise.inject.spi.Extension {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLDCDIExtension.class);

    public <T> void processAnnotatedType(
            @WithAnnotations({QueryRepository.class,QueryRepositories.class}) @Observes final ProcessAnnotatedType<T> pat, final BeanManager bm) {

        final Set<Annotation> annotated = pat.getAnnotatedType().getAnnotations();
        final String className = pat.getAnnotatedType().getClass().getName();
        
        for (final Annotation annotation : annotated) {
            if (annotation instanceof QueryRepository) {
                final String prefixLookup = ((QueryRepository) annotation).value();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Query repository detected {}, prefix {}", className, prefixLookup);
                }
                SQLDClassPathLoader.loadSqlds(prefixLookup);
            } else if (annotation instanceof QueryRepositories) {
                QueryRepository[] repositories =  ((QueryRepositories) annotation).value();
                for (QueryRepository repository: Arrays.asList(repositories)) {
                    final String prefixLookup = repository.value();
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Query repository detected {}, prefix {}", className, prefixLookup);
                    }
                    SQLDClassPathLoader.loadSqlds(prefixLookup);
                }
            }
        }
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        LOGGER.debug("Clear QueryDefinitionsHolder");
        QueryDefinitionsHolder.clear(); 
    }

}
