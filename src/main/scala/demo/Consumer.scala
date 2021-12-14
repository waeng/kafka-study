package demo

import java.time.Duration
import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.KafkaConsumer

object Consumer {

  def main(args: Array[String]): Unit = {

    val properties = new Properties()
    //kafka集群，broker-list
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("group.id", "consumer")
    properties.put("key.deserializer",
      "org.apache.kafka.common.serialization.StringDeserializer")
    properties.put("value.deserializer",
      "org.apache.kafka.common.serialization.StringDeserializer")

    val consumer = new KafkaConsumer[String, String](properties)
    consumer.subscribe(Collections.singletonList("demo"))

    while (true) {
      val records = consumer.poll(Duration.ofMillis(10))
      records.forEach(record => {
        println("topic = %s, partition = %s, offset = %d, key = %s, value = %s\n".format(record.topic(), record.partition(), record.offset(),
          record.key(), record.value())
        )
      })
    }

  }
}
