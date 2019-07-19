package kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerDemo {
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("zk.connect", "weekend5:2181,weekend6:2181,weekend7:2181");
		prop.setProperty("metadata.broker.list", "weekend5:9092,weekend6:9092,weekend7:9092");
		prop.setProperty("serializer.class", "kafka.serializer.StringEncoder");
		ProducerConfig config = new ProducerConfig(prop);
		Producer<String,String>  producer = new Producer<String,String>(config);
		for (int i=0;i<=100;i++) {
			producer.send(new KeyedMessage<String, String>("mygirls","tingting"+i ));
		}
	}
}
