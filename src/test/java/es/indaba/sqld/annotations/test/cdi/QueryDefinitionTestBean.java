/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU Lesser General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>
 * 
 *******************************************************************************/
package es.indaba.sqld.annotations.test.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import es.indaba.sqld.annotations.cdi.api.QueryRepository;
import es.indaba.sqld.annotations.cdi.api.QueryResolver;
import es.indaba.sqld.api.QueryDefinition;

@ApplicationScoped
@QueryRepository("es.indaba.sqld.test.sqld")
@QueryRepository("es.indaba.sqld.test.yaml")
public class QueryDefinitionTestBean {

    @Inject
    @QueryResolver("QUERY1")
    private String query1AsString;

    @Inject
    @QueryResolver("QUERY2")
    private String query2AsString;

    @Inject
    @QueryResolver("QUERY1")
    private QueryDefinition query1AsQueryDefinition;

    public String getQuery1AsString() {
        return query1AsString;
    }

    public void setQuery1AsString(String query1AsString) {
        this.query1AsString = query1AsString;
    }

    public String getQuery2AsString() {
        return query2AsString;
    }

    public void setQuery2AsString(String query2AsString) {
        this.query2AsString = query2AsString;
    }

    public QueryDefinition getQuery1AsQueryDefinition() {
        return query1AsQueryDefinition;
    }

    public void setQuery1AsQueryDefinition(QueryDefinition query1AsQueryDefinition) {
        this.query1AsQueryDefinition = query1AsQueryDefinition;
    }



}
