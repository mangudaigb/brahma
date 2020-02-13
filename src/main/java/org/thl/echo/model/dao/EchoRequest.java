package org.thl.echo.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EchoRequest {
    private String message;
    @JsonProperty(value = "dependents")
    private List<Dependent> dependentList = new ArrayList<>();

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
