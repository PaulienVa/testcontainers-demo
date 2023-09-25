package nl.openvalue.meetup.testcontainers.demo.services

import nl.openvalue.meetup.testcontainers.demo.persistence.Student
import nl.openvalue.meetup.testcontainers.demo.persistence.StudentDao
import nl.openvalue.meetup.testcontainers.demo.subscriptions.StudentRegistrationMessage
import org.springframework.stereotype.Service
import java.util.*
import nl.openvalue.meetup.testcontainers.demo.api.Student as StudentApi

@Service
class StudentService(private val studentDao: StudentDao) {

    fun processStudentRegistration(message: StudentRegistrationMessage) {
        println("Persisting message")
        studentDao.save(
            Student(
                name = message.name,
                yearOfRegistration = message.yearOfRegistration,
                mainCourse = message.mainCourse
            )
        )
    }

    fun retrieveStudent(studentName: String): Optional<StudentApi> {
        val optionalStudent = studentDao.findByName(studentName)
        return if (optionalStudent.isPresent) {
            val student = optionalStudent.get()
            Optional.of(
                nl.openvalue.meetup.testcontainers.demo.api.Student(
                    student.name,
                    student.yearOfRegistration,
                    student.mainCourse
                )
            )
        } else {
            Optional.empty<nl.openvalue.meetup.testcontainers.demo.api.Student>()
        }
    }
}