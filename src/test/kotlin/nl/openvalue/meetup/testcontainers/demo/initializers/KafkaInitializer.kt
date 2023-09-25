package nl.openvalue.meetup.testcontainers.demo.initializers

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName

class KafkaInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {


    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
            .withEnv("KAFKA_AUTO_CREATE_TOPICS_ENABLE", "true")
            .withEnv("KAFKA_CREATE_TOPICS", "students")

        kafka.start()

        println("Kafka config : ${kafka.bootstrapServers}")
        TestPropertyValues.of(
            "spring.kafka.bootstrap-servers=${kafka.bootstrapServers}"
        ).applyTo(applicationContext.environment)
    }
}

