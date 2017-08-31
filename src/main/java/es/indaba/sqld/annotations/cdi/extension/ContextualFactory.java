/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU Lesser General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>
 * 
 *******************************************************************************/
package es.indaba.sqld.annotations.cdi.extension;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;

import org.apache.deltaspike.core.util.metadata.builder.ContextualLifecycle;

import es.indaba.sqld.api.QueryDefinitionRepository;

public class ContextualFactory implements ContextualLifecycle<QueryDefinitionRepository> {

    @Override
    public QueryDefinitionRepository create(Bean<QueryDefinitionRepository> bean,
            CreationalContext<QueryDefinitionRepository> creationalContext) {
        return new QueryDefinitionRepository();
    }

    @Override
    public void destroy(Bean<QueryDefinitionRepository> bean, QueryDefinitionRepository instance,
            CreationalContext<QueryDefinitionRepository> creationalContext) {
        // TODO Auto-generated method stub

    }
}
