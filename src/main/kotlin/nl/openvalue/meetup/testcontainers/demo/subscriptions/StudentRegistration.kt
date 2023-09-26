package nl.openvalue.meetup.testcontainers.demo.subscriptions

import mu.KotlinLogging
import nl.openvalue.meetup.testcontainers.demo.services.StudentService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class StudentRegistration(private val studentService: StudentService) {
    private val logger = KotlinLogging.logger {}


    @KafkaListener(topics = ["students"], groupId = "demo")
    fun listenStudents(message: StudentRegistrationMessage) {
        logger.info { "Receiving message in the Kafka listener $message" }
        studentService.processStudentRegistration(message)
    }
}

data class StudentRegistrationMessage(
    val name: String,
    val yearOfRegistration: Int,
    val mainCourse: String
)