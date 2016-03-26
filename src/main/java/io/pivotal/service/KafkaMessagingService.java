package io.pivotal.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
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

    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;

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
        consumer = new KafkaConsumer<>(kafkaProps);
    }

    public void send(String message) throws ExecutionException,
            InterruptedException, IOException {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);

        if ("sync".equalsIgnoreCase(sync)) {
            sendSync(record);
        } else {
            sendAsync(record);
        }
    }

    private void sendSync(ProducerRecord<String, String> record) throws ExecutionException,
            InterruptedException, IOException {
        producer.send(record).get();
    }

    private void sendAsync(ProducerRecord<String, String> record) throws IOException {
        producer.send(record, (RecordMetadata recordMetadata, Exception e) -> {
            if (e != null) {
                e.printStackTrace();
            }
        });
    }
}
