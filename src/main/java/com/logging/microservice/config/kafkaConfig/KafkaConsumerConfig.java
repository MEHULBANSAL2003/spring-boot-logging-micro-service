package com.logging.microservice.config.kafkaConfig;


import com.logging.microservice.dto.kafkaLogEventDto.KafkaLogEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;
  @Value("${spring.kafka.consumer.group-id}")
  private String serviceGroup;
  @Value("${spring.kafka.consumer.auto-offset-reset}")
  private String resetConfig;


  @Bean
  public ConsumerFactory<String, KafkaLogEvent> consumerFactory(){
    JsonDeserializer<KafkaLogEvent> jsonDeserializer =
      new JsonDeserializer<>(KafkaLogEvent.class);
    jsonDeserializer.addTrustedPackages("*");
    jsonDeserializer.ignoreTypeHeaders();

    Map<String, Object> config = new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    config.put(ConsumerConfig.GROUP_ID_CONFIG, serviceGroup);
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, resetConfig);

    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jsonDeserializer);

    // performance optimisation for logging
    config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);
    config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
  }
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, KafkaLogEvent> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, KafkaLogEvent> factory =
      new ConcurrentKafkaListenerContainerFactory<>();

    factory.setConsumerFactory(consumerFactory());

    // enable parallel consumers
    factory.setConcurrency(3);

    // in case JSON has errors
    factory.setRecordInterceptor((ex, record) -> {
      System.err.println("Error while consuming: " + ex.getClass());
      System.err.println("Bad message: " + record);
      return ex;
    });
    return factory;
  }
}

