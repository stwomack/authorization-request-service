package io.pivotal.controller;

import io.pivotal.domain.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorizationRequestController {

    @RequestMapping(value="/authorizationRequest", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody AuthorizationRequest authorizationRequest(
            @RequestBody AuthorizationRequest authorizationRequest) {

//        messagingService.sendMessage(authorizationRequest);
        System.out.println(String.format("Payment saved and put on queue = [%s]", authorizationRequest));
        return authorizationRequest;
    }
}
