package org.thl.echo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class EchoReply {
    @JsonProperty("dependents")
    private List<EchoReply> dependentEchoReplyList;
    private String name;
    private Integer depth;
    @JsonProperty("time")
    private String responseTime;
    private String message;

    public List<EchoReply> getDependentEchoReplyList() {
        return dependentEchoReplyList;
    }

    public void setDependentEchoReplyList(List<EchoReply> dependentEchoReplyList) {
        this.dependentEchoReplyList = dependentEchoReplyList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
