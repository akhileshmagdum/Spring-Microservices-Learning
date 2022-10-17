package learn;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {
    public static final Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);

    public static void main(String[] args) {
        logger.info("This consumer");

//      Consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"group1");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

//      Creating consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

//      Get reference to the current thread
        final Thread mainThread = Thread.currentThread();

//      Adding the shutdown hook
//      Standard way in Java to create a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Shutdown detected!");
                consumer.wakeup();

//              join the main thread to allow the execution of the code in the main thread
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        });

        try {
//          Subscribing consumer to a topic
            consumer.subscribe(Collections.singleton("topic1"));

//          Infinite loop to read messages from the topic
            while (true) {
                logger.info("Pulling");
//              On each poll, consumer will try to use the last consumed offset as the starting offset and fetch sequentially
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    logger.info("record received");
                    System.out.println(record.key() + "\n" + record.value());
                }
            }
        } catch (WakeupException e) {
            logger.error("Excepted error occurred!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//          This will also commit the offsets if needed to be
            consumer.close();
            logger.info("Gracefully closed the consumer");
        }
    }
}
