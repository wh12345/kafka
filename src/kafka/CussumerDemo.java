package kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

public class CussumerDemo {
	private static final String topic = "mygirls";
	private static final Integer threads =1;
	public static void main(String[] args) {
		Properties prop  = new Properties();
		prop.setProperty("zookeeper.connect", "weekend5:2181,weekend6:2181,weekend7:2181");
		prop.setProperty("group.id", "1111");
		prop.setProperty("auto.offset.reset", "smallest");
		ConsumerConfig config = new ConsumerConfig(prop);
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
		Map<String,Integer>  topicCountMap = new HashMap<String,Integer>();
		topicCountMap.put(topic, 1);
		Map<String, List<KafkaStream<byte[],byte[]>>>  consumerMap  = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[],byte[]>> streams = consumerMap.get(topic);
		for ( final KafkaStream<byte[], byte[]> kafkaStream : streams) {
			new Thread(
					new Runnable() {						
						@Override
						public void run() {
							for (MessageAndMetadata<byte[], byte[]> mm : kafkaStream) {
								String msg = new String(mm.message());
								System.out.println(msg);
							}							
						}
					}
					).start();
		}
	}
}
