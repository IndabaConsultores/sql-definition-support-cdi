/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU Lesser General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>
 * 
 *******************************************************************************/
package es.indaba.sqld.annotations.test;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.junit.Assert;
import org.junit.Test;

import es.indaba.sqld.annotations.test.cdi.QueryDefinitionTestBean;
import es.indaba.sqld.api.QueryDefinition;

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
