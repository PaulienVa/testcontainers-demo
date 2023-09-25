package nl.openvalue.meetup.testcontainers.demo.initializers

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.testcontainers.containers.PostgreSQLContainer
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.listDirectoryEntries

class PostgresInitializer: ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        assertThatDbScriptExists()

        println("Starting Postgres container...")
        postgresContainer.start()
        println("Started Postgres container on ${postgresContainer.jdbcUrl}")

        TestPropertyValues.of(
            "spring.datasource.url=${postgresContainer.jdbcUrl}",
            "spring.datasource.password=${postgresContainer.password}",
            "spring.datasource.username=${postgresContainer.username}"
        ).applyTo(applicationContext.environment)

        applicationContext.addApplicationListener {
            if (it is ContextClosedEvent) {
                println("Stopping Postgres container...")
                postgresContainer.stop()
            }
        }
    }

    companion object {

        private val PATH_TO_SCHEMA = "src/main/resources/db/migration"

        val postgresContainer = PostgreSQLContainer("postgres:13")
            .apply {
                withDatabaseName("postgres")
                withUsername("postgres")
                withPassword("postgres")
            }
    }

    private fun assertThatDbScriptExists() {
        val path: Path =
            Paths.get(PATH_TO_SCHEMA)
        if (Files.isDirectory(path)) {
            if (path.listDirectoryEntries("*.sql").isEmpty()) {
                throw RuntimeException("🛑 Migrations not found. Please run `get-db-schema.sh` first!")
            }
        } else {
            throw RuntimeException("🛑 Migration folder not found")
        }
    }
}