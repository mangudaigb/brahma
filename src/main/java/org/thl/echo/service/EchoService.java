package org.thl.echo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thl.echo.model.dao.Dependent;
import org.thl.echo.model.dao.EchoRequest;
import org.thl.echo.model.dto.EchoReply;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Singleton
public class EchoService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS z");
    private RxHttpClient httpClient;
    private static final Logger logger = LoggerFactory.getLogger(EchoService.class);

    public EchoReply createResponse(EchoRequest request) {
        String serviceName = System.getProperty("name");
        logger.debug("System Name: " + serviceName);
//        Integer depth = Integer.valueOf();
        EchoReply er = new EchoReply();
        er.setName(serviceName);
        er.setResponseTime(getDate());
        er.setMessage(request.getMessage());

        List<EchoReply> dependentReplyList = new ArrayList<>();
        for (Dependent dependent : request.getDependentList()) {
            EchoReply response = callDependent(dependent);
            dependentReplyList.add(response);
        }
        er.setDependentEchoReplyList(dependentReplyList);
        return er;
    }

    private EchoReply callDependent(Dependent dependent) {
        try {
            URL url = new URL(dependent.getUrl());
            httpClient = RxHttpClient.create(url);
            ObjectMapper obj = new ObjectMapper();
            EchoRequest req = new EchoRequest();
            req.setMessage(dependent.getMessage());
            req.setDependentList(dependent.getDependentList());
            HttpResponse<EchoReply> response = httpClient.toBlocking().exchange(
                    HttpRequest.POST("/echo", req)
                        .contentType(MediaType.APPLICATION_JSON),
                    EchoReply.class);
            EchoReply reply = response.getBody().get();
            return reply;
        } catch (MalformedURLException ex) {
            logger.error("Error connecting to service url: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        return null;
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }
}
