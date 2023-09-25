package nl.openvalue.meetup.testcontainers.demo.subscriptions

import nl.openvalue.meetup.testcontainers.demo.services.StudentService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class StudentRegistration(private val studentService: StudentService) {

    @KafkaListener(topics = ["students"], groupId = "demo")
    fun listenGroupFoo(message: StudentRegistrationMessage) {
        studentService.processStudentRegistration(message)
    }
}

data class StudentRegistrationMessage(
    val name: String,
    val yearOfRegistration: Number,
    val mainCourse: String
)