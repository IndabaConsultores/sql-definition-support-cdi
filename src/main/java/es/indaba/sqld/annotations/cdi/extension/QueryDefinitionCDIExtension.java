package es.indaba.sqld.annotations.cdi.extension;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.core.util.bean.BeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.indaba.sqld.annotations.cdi.api.QueryRepositories;
import es.indaba.sqld.annotations.cdi.api.QueryRepository;
import es.indaba.sqld.api.QueryDefinitionRepository;
import es.indaba.sqld.impl.loader.QueryDefinitionClassPathLoader;

public class QueryDefinitionCDIExtension implements javax.enterprise.inject.spi.Extension {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDefinitionCDIExtension.class);

    private Bean<QueryDefinitionRepository> bean;

    private final Set<String> repositoryPrefixes = new HashSet<>();

    public <T> void processAnnotatedType(@WithAnnotations({QueryRepository.class,
            QueryRepositories.class}) @Observes final ProcessAnnotatedType<T> pat, final BeanManager bm) {

        final Set<Annotation> annotated = pat.getAnnotatedType().getAnnotations();
        final String className = pat.getAnnotatedType().getBaseType().getTypeName();

        for (final Annotation annotation : annotated) {
            if (annotation instanceof QueryRepository) {
                final String prefixLookup = ((QueryRepository) annotation).value();
                repositoryPrefixes.add(prefixLookup);
                LOGGER.debug("Query repository detected {}, prefix {}", className, prefixLookup);
                registerRepositoryBean(bm);
            } else if (annotation instanceof QueryRepositories) {
                final QueryRepository[] repositories = ((QueryRepositories) annotation).value();
                registerRepositoryBean(bm);
                for (final QueryRepository repository : Arrays.asList(repositories)) {
                    final String prefixLookup = repository.value();
                    repositoryPrefixes.add(prefixLookup);
                    LOGGER.debug("Query repository detected {}, prefix {}", className, prefixLookup);
                }
            }
        }
    }

    public void afterBeanDiscovery(@Observes final AfterBeanDiscovery abd, final BeanManager bm) {
        if (bean != null) {
            abd.addBean(bean);
            bean = null;
        }

    }

    public void AfterDeploymentValidation(@Observes final AfterDeploymentValidation adv, final BeanManager bm) {
        if (!repositoryPrefixes.isEmpty()) {

            final QueryDefinitionRepository repository =
                    BeanProvider.getContextualReference(QueryDefinitionRepository.class);
            for (final String prefixLookup : repositoryPrefixes) {
                QueryDefinitionClassPathLoader.loadQueryDefinitionFiles(prefixLookup, repository);
            }

            repositoryPrefixes.clear();
        }
    }

    private void registerRepositoryBean(final BeanManager bm) {

        if (bean == null) {
            final BeanBuilder<QueryDefinitionRepository> beanBuilder = new BeanBuilder<QueryDefinitionRepository>(bm)
                    .passivationCapable(false).beanClass(QueryDefinitionRepository.class).scope(ApplicationScoped.class)
                    .name(QueryDefinitionRepository.class.getName()).beanLifecycle(new ContextualFactory());
            bean = beanBuilder.create();
        }

    }

}
