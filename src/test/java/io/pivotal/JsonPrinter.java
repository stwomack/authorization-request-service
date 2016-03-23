package io.pivotal;

import io.pivotal.domain.AuthorizationRequest;
import kafka.utils.Json;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class JsonPrinter {

    @Test
    public void printIt() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(
                new Random().nextLong(), new Random().nextLong(),
                new Random().nextLong(), new Random().nextLong(),
                new Random().nextDouble(), "stuff", new Date());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(authorizationRequest));
    }


}
