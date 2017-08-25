package es.indaba.sqld.annotations.test;



import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    private static CdiContainer cdiContainer;
    private static ContextControl contextControl;

    @BeforeClass
    public static void setUp() {
        cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();

        contextControl = cdiContainer.getContextControl();
        contextControl.startContext(ApplicationScoped.class);
    }

    @AfterClass
    public static void tearDown() {
        contextControl.stopContexts();
        cdiContainer.shutdown();
    }


}
