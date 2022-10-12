package learn;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class ProducerDemo {
    public static final Logger log = LoggerFactory.getLogger(ProducerDemo.class);

    public static void main(String[] args) {
        log.info("Printing!");

//      Creating producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

//      Create the producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

//      Create producer record
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("topic1","hello world");

//      Send the data - asynchronous
//      Using this method alone will not be sufficient because the data might not be written into kafka before the program closes
        producer.send(producerRecord);

//      flush data - synchronous
        producer.flush();

//      close and flush
        producer.close();
    }
}
