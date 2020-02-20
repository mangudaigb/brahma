package org.thl.echo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.context.ServerRequestContext;
import org.thl.echo.model.dao.EchoConfiguration;
import org.thl.echo.model.dao.EchoRequest;
import org.thl.echo.model.dto.EchoReply;
import org.thl.echo.service.EchoService;

import javax.inject.Inject;

@Controller
public class EchoController {

    private EchoService echoService;

    public EchoController(EchoService echoService) {
        this.echoService = echoService;
    }

    @Put(value = "/echo")
    @Produces(MediaType.APPLICATION_JSON)
    public EchoReply getResponseForEcho(@Body EchoRequest request) {
        HttpRequest hr = ServerRequestContext.currentRequest().get();
        String uri = hr.getUri().toString();
        String hostname = hr.getServerName();
        return echoService.createResponse(request, hostname + uri);
    }

    @Post(produces = MediaType.APPLICATION_JSON)
    public EchoConfiguration configurePath(){
        return null;
    }

}
