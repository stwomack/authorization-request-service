package io.pivotal.controller;

import io.pivotal.domain.*;
import io.pivotal.service.KafkaMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class AuthorizationRequestController {

    @Autowired
    KafkaMessagingService messagingService;

    @RequestMapping(value="/authorizationRequest", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody AuthorizationRequest authorizationRequest(
            @RequestBody AuthorizationRequest authorizationRequest) throws ExecutionException, InterruptedException {

        messagingService.send(authorizationRequest);
        System.out.println(String.format("Payment saved and put on queue = [%s]", authorizationRequest));
        return authorizationRequest;
    }
}
