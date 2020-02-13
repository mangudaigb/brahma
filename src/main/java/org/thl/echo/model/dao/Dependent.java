package org.thl.echo.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Dependent {
    private String url;
    private String message;
    @JsonProperty("dependents")
    private List<Dependent> dependentList = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Dependent> getDependentList() {
        return dependentList;
    }

    public void setDependentList(List<Dependent> dependentList) {
        this.dependentList = dependentList;
    }
}
