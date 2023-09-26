package nl.openvalue.meetup.testcontainers.demo

import nl.openvalue.meetup.testcontainers.demo.api.Student
import nl.openvalue.meetup.testcontainers.demo.initializers.KafkaInitializer
import nl.openvalue.meetup.testcontainers.demo.initializers.PostgresInitializer
import nl.openvalue.meetup.testcontainers.demo.subscriptions.StudentRegistrationMessage
import org.assertj.core.api.Assertions.assertThat
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [PostgresInitializer::class, KafkaInitializer::class])
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = ["test"])
class TestcontainersDemoApplicationTests {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, StudentRegistrationMessage>

    @Value(value = "\${local.server.port}")
    private val port = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `when consuming from kafka then student is retrieved`() {

        kafkaTemplate.send(
            "students",
            Random.nextInt().toString(),
            StudentRegistrationMessage("john", 2023, "mathematics")
        )

        await().pollInterval(Duration.ofSeconds(3))
            .atMost(30, TimeUnit.SECONDS)
            .untilAsserted {
                val studentFromAPI = restTemplate.getForObject(
                    "http://localhost:$port/students/john",
                    Student::class.java
                )
                assertThat(studentFromAPI.name).isEqualTo("john")
            }
    }
}
