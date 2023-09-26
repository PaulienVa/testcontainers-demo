package nl.openvalue.meetup.testcontainers.demo.initializers

import mu.KotlinLogging
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.testcontainers.containers.PostgreSQLContainer

class PostgresInitializer: ApplicationContextInitializer<ConfigurableApplicationContext> {

    private val logger = KotlinLogging.logger {}

    private val postgresContainer = PostgreSQLContainer("postgres:13")
            .apply {
                withDatabaseName("postgres")
                withUsername("postgres")
                withPassword("postgres")
            }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        logger.info { "Starting Postgres container..." }
        postgresContainer.start()
        logger.info {"Started Postgres container on ${postgresContainer.jdbcUrl}" }

        TestPropertyValues.of(
            "spring.datasource.url=${postgresContainer.jdbcUrl}",
            "spring.datasource.password=${postgresContainer.password}",
            "spring.datasource.username=${postgresContainer.username}"
        ).applyTo(applicationContext.environment)

        applicationContext.addApplicationListener {
            if (it is ContextClosedEvent) {
                logger.info { "Stopping Postgres container..." }
                postgresContainer.stop()
            }
        }
    }
}


