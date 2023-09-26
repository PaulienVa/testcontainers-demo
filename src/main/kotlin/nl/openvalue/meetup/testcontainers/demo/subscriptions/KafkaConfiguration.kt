package nl.openvalue.meetup.testcontainers.demo.subscriptions


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