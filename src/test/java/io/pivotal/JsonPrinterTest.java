package io.pivotal;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.pivotal.domain.AuthorizationRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public AuthorizationRequest createTestPayload() {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(
                randLong(), randLong(), getRandomCardNumber(),
                Math.abs(new Random().nextDouble()), RandomStringUtils.randomAlphabetic(8), new Date());
        return authorizationRequest;
    }

    private long randLong() {
        Long min = new Long(10000000);
        Long max = new Long(99999999);

        return Math.abs(randomLong(min, max));
    }

    private long randomLong(long min, long max) {
        return (new Random().nextLong() % (max - min)) + min;
    }

    private String getRandomCardNumber() {
        List<String> cardList = new ArrayList<String>();
        cardList.add("5105 1051 0510 5100");
        cardList.add("5185 5408 1000 0019");
        cardList.add("5204 2300 8000 0017");
        cardList.add("5204 7400 0990 0014");
        cardList.add("5420 9238 7872 4339");
        cardList.add("5455 3307 6000 0018");
        cardList.add("5506 9208 0924 3667");
        cardList.add("5506 9215 0542 1029");
        return cardList.get(new Random().nextInt(cardList.size()));
    }
}
