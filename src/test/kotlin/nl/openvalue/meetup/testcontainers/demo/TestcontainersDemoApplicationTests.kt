package nl.openvalue.meetup.testcontainers.demo

import nl.openvalue.meetup.testcontainers.demo.subscriptions.StudentRegistrationMessage
import org.apache.kafka.clients.producer.Producer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import kotlin.random.Random
import kotlin.random.nextInt

@SpringBootTest
class TestcontainersDemoApplicationTests : AbstractIT() {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, StudentRegistrationMessage>

    @Test
    fun `when consuming from kafka then student is retrieved`() {

        kafkaTemplate.send("students", Random.nextInt().toString(), StudentRegistrationMessage("name", 2023, "mainCourse"))

    }

}
