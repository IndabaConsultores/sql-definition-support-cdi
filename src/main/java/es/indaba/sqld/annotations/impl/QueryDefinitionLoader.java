/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU Lesser General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>
 * 
 *******************************************************************************/
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