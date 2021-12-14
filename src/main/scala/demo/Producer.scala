package demo

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import java.util.Properties

object Producer {

  def main(args: Array[String]): Unit = {
    val props = new Properties()
    //kafka集群，broker-list
    props.put("bootstrap.servers", "localhost:9092")
    props.put("acks", "all")
    props.put("retries", 1); //重试次数
    props.put("batch.size", 16384) //批次大小
    props.put("linger.mx", 1) //等待时间
    props.put("buffer.memory", 33554432) ///RecordAccumulator 缓冲区大小

    props.put("key.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    for (j <- 1 to 10) {
      new Thread(
        () => {
          for (i <- 1 to 100000) {
            producer.send(new ProducerRecord[String, String]("demo", i + "key", i + "value" + s"线程-$j"))
          }
        }, s"thread-$j"
      ).start()
    }

  }
}