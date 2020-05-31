package uci.middleware.project.messaging;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

public class ConsumerAndProducer {


    public static void runConsumer(String topicName) {
        Consumer<Long, String> consumer = ConsumerCreator.createConsumer(topicName);
        int noMessageFound = 0;
        while (true) {
            ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
            // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
            if (consumerRecords.count() == 0) {
                noMessageFound++;
                if (noMessageFound > KafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                    // If no message found count is reached to threshold exit loop.
                    break;
                else
                    continue;
            }
            //print each record.
            consumerRecords.forEach(record -> {
                System.out.println("Record Key " + record.key());
                System.out.println("Record value " + record.value());
                System.out.println("Record partition " + record.partition());
                System.out.println("Record offset " + record.offset());
            });
            // commits the offset of record to broker.
            consumer.commitSync();
        }
        consumer.close();
    }

    public static void runConsumerSinglePoll(String topicName) {

        Consumer<Long, String> consumer = ConsumerCreator.createConsumer(topicName);
        int noMessageFound = 0;

        ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
        //print each record.
        consumerRecords.forEach(record -> {
            System.out.println("Record Key " + record.key());
            System.out.println("Record value " + record.value());
            System.out.println("Record partition " + record.partition());
            System.out.println("Record offset " + record.offset());
        });
        // commits the offset of record to broker.

        consumer.commitSync();
        consumer.close();
    }

    public static void runProducer(String topicName) {
        Producer<Long, String> producer = ProducerCreator.createProducer();
        for (int index = 0; index < KafkaConstants.MESSAGE_COUNT; index++) {
            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(topicName,
                    "This is record " + index);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            } catch (ExecutionException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            } catch (InterruptedException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
    }
}
