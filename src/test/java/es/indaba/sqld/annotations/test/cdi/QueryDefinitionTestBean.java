package es.indaba.sqld.annotations.test.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import es.indaba.sqld.QueryDefinition;
import es.indaba.sqld.annotations.api.QueryResolver;

@ApplicationScoped
public class QueryDefinitionTestBean {

    @Inject
    @QueryResolver(name = "QUERY1")
    private String query1AsString;

    @Inject
    @QueryResolver(name = "QUERY2")
    private String query2AsString;

    @Inject
    @QueryResolver(name = "QUERY1")
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
