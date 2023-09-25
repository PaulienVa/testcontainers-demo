package nl.openvalue.meetup.testcontainers.demo.subscriptions

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


//@EnableKafka
//@Configuration
//class KafkaConsumerConfig(@Value("kafka.config.bootstrap-server") private val bootstrapAddress: String) {
//    @Bean
//    fun consumerFactory(): ConsumerFactory<String, StudentRegistrationMessage> {
//        val props: MutableMap<String, Any> = HashMap()
//        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
//        props[ConsumerConfig.GROUP_ID_CONFIG] = "testcontainers"
//        return DefaultKafkaConsumerFactory(props,
//            StringDeserializer(),
//            JsonDeserializer(StudentRegistrationMessage::class.java))
//    }
//
//    @Bean
//    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, StudentRegistrationMessage> {
//        val factory = ConcurrentKafkaListenerContainerFactory<String, StudentRegistrationMessage>()
//        factory.setConsumerFactory(consumerFactory())
//        return factory
//    }
//}