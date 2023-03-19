package com.amyojiakor.Report.Application.config;

import com.amyojiakor.Report.Application.models.dto.CustomerOrderResponse;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
   @Value("${spring.kafka.bootstrap-servers}")
   private String bootstrapServers;
   @Value("${spring.kafka.consumer.group-id}")
   private String groupId;

   @Bean
   public Map<String, Object> consumerFactory() {
      Map<String, Object> props = new HashMap<>();
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
      props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
      return props;
   }

   @Bean
   public ConcurrentKafkaListenerContainerFactory<String, CustomerOrderResponse> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, CustomerOrderResponse> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerFactory(), new StringDeserializer(), new JsonDeserializer<>(CustomerOrderResponse.class, false)));
      return factory;
   }
}
