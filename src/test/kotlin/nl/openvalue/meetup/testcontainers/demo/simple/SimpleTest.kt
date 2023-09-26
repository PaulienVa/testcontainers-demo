package nl.openvalue.meetup.testcontainers.demo.simple

import mu.KotlinLogging
import nl.openvalue.meetup.testcontainers.demo.persistence.Student
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@Testcontainers
class SimpleTest {

    val logger = KotlinLogging.logger {}

    private val USERNAME = "postgres"
    private val PASSWORD = "postgres"

    @Container
    private val postgresContainer = PostgreSQLContainer("postgres:13")
        .apply {
            withDatabaseName("postgres")
            withUsername(USERNAME)
            withPassword(PASSWORD)
            withInitScript("db/schema/V0001__create_students.sql")
        }


    private lateinit var connection: Connection

    @BeforeEach
    fun configure() {
        val jdbcUrl = postgresContainer.getJdbcUrl()
        connection = DriverManager.getConnection(jdbcUrl, USERNAME, PASSWORD)
    }

    @Test
    fun `test query`() {

        // insert data
        try {
            val prepareStatement = connection.prepareStatement(
                """
                INSERT INTO students(student_id, name, year_of_registration, main_course) 
                VALUES (11, 'betty', 2009, 'mathematics');
            """.trimIndent()
            )
            prepareStatement.execute()
        } catch (ex : SQLException) {
            logger.error { "An error inserting the data: $ex" }
        }


        // retrieve data
        val students = mutableListOf<Student>()
        try {
            val prepareStatement = connection.prepareStatement(
                """
                    SELECT student_id, name, year_of_registration, main_course FROM students;
                """.trimIndent()
            )
            val resultSet = prepareStatement.executeQuery()
            while (resultSet.next()) {
                val id = resultSet.getLong("student_id")
                val name = resultSet.getString("name")
                val year = resultSet.getInt("year_of_registration")
                val mainCourse = resultSet.getString("main_course")
                students.add(Student(id, name, year, mainCourse))
            }
        } catch (ex : SQLException) {
            logger.error { "An error retrieving the data: $ex" }
        }

        assertThat(students).hasSize(1)
        assertThat(students[0].name).isEqualTo("betty")
        assertThat(students[0].yearOfRegistration).isEqualTo(2009)
        assertThat(students[0].mainCourse).isEqualTo("mathematics")

    }

}