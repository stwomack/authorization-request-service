package io.pivotal;

import io.pivotal.domain.AuthorizationRequest;
import kafka.utils.Json;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class JsonPrinterTest {

    @Test
    public void printIt() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(
                randLong(), randLong(), randLong(), randLong(),
                Math.abs(new Random().nextDouble()), RandomStringUtils.randomAlphabetic(8), new Date());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(authorizationRequest));
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
