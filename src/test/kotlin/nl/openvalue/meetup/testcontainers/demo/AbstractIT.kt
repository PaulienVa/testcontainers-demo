package nl.openvalue.meetup.testcontainers.demo

import nl.openvalue.meetup.testcontainers.demo.initializers.KafkaInitializer
import nl.openvalue.meetup.testcontainers.demo.initializers.PostgresInitializer
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers


@ContextConfiguration(initializers = [PostgresInitializer::class, KafkaInitializer::class])
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = ["test"])
abstract class AbstractIT