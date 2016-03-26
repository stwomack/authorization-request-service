package io.pivotal;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.pivotal.domain.AuthorizationRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class JsonPrinterTest {
    ObjectMapper mapper = new ObjectMapper();

    public static final String URL = "http://localhost:8080/authorizationRequest";
    private static final int MESSAGE_COUNT = 1;

    @Test
    public void spamHttpEndpointWithMessages() throws UnirestException, IOException {
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String payload = mapper.writeValueAsString(createTestPayload());
            System.out.println(String.format("Payment sent. [%s]", payload));

            Unirest.post(URL)
                    .header("Content-Type", "application/json")
                    .body(payload)
                    .asJson();
        }
    }

    public AuthorizationRequest createTestPayload() throws IOException {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(
                randLong(), randLong(), randLong(), randLong(),
                Math.abs(new Random().nextDouble()), RandomStringUtils.randomAlphabetic(8), new Date());
        return authorizationRequest;
    }

    private long randLong() {
        Long min = new Long(10000000);
        Long max = new Long(99999999);

        return Math.abs(randomLong(min, max));
    }

    private long randomLong(long min, long max) {
        return (new java.util.Random().nextLong() % (max - min)) + min;
    }
}
