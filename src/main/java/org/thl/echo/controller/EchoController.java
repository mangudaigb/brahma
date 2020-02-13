package org.thl.echo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
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

    @Post(value = "/echo")
    @Produces(MediaType.APPLICATION_JSON)
    public EchoReply getResponseForEcho(@Body EchoRequest request) {
        return echoService.createResponse(request);
    }

    @Post(produces = MediaType.APPLICATION_JSON)
    public EchoConfiguration configurePath(){
        return null;
    }

}
