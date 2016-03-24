package io.pivotal.service;

import io.pivotal.domain.AuthorizationRequest;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaMessagingService {
    @Value("${brokerList}")
    private String brokerList;

    @Value("${sync}")
    private String sync;

    @Value("${topic}")
    private String topic;

    private Producer<String, String> producer;

    public KafkaMessagingService() {
    }

    @PostConstruct
    public void init() {
        Properties kafkaProps = new Properties();

        kafkaProps.put("bootstrap.servers", brokerList);

        kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("acks", "1");

        kafkaProps.put("retries", "1");
        kafkaProps.put("linger.ms", 5);

        producer = new KafkaProducer<>(kafkaProps);

    }

    public void send(AuthorizationRequest authorizationRequest) throws ExecutionException,
            InterruptedException {
        if ("sync".equalsIgnoreCase(sync)) {
            sendSync(authorizationRequest);
        } else {
            sendAsync(authorizationRequest);
        }
    }

    private void sendSync(AuthorizationRequest authorizationRequest) throws ExecutionException,
            InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, authorizationRequest.toString());
        producer.send(record).get();

    }

    private void sendAsync(AuthorizationRequest authorizationRequest) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, authorizationRequest.toString());

        producer.send(record, (RecordMetadata recordMetadata, Exception e) -> {
            if (e != null) {
                e.printStackTrace();
            }
        });
    }
}
