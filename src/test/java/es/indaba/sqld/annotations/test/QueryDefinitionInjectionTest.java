package es.indaba.sqld.annotations.test;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.junit.Assert;
import org.junit.Test;

import es.indaba.sqld.QueryDefinition;
import es.indaba.sqld.annotations.test.cdi.QueryDefinitionTestBean;

public class QueryDefinitionInjectionTest extends AbstractTest {

    @Test
    public void injectionTest() {
        QueryDefinitionTestBean queryDefinitionBean =
                BeanProvider.getContextualReference(QueryDefinitionTestBean.class, false);
        Assert.assertEquals("QUERY1_CONTENT", queryDefinitionBean.getQuery1AsString());
        Assert.assertEquals("QUERY2_CONTENT", queryDefinitionBean.getQuery2AsString());

        QueryDefinition query1Definition = queryDefinitionBean.getQuery1AsQueryDefinition();
        Assert.assertEquals("QUERY1_CONTENT", query1Definition.getQueryAsString());
        
        query1Definition = queryDefinitionBean.getQuery1AsQueryDefinition();
        Assert.assertEquals("QUERY1_CONTENT", query1Definition.getQueryAsString());
    }

}
