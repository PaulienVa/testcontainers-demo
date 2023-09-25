package nl.openvalue.meetup.testcontainers.demo.services

import nl.openvalue.meetup.testcontainers.demo.persistence.Student
import nl.openvalue.meetup.testcontainers.demo.persistence.StudentDao
import nl.openvalue.meetup.testcontainers.demo.subscriptions.StudentRegistrationMessage
import org.springframework.stereotype.Service
import nl.openvalue.meetup.testcontainers.demo.api.Student as StudentApi

@Service
class StudentService(private val studentDao: StudentDao) {

    fun processStudentRegistration(message: StudentRegistrationMessage) {
        studentDao.save(Student(name = message.name, yearOfRegistration = message.yearOfRegistration, mainCourse = message.mainCourse))
    }

    fun retrieveStudent(studentName: String): StudentApi {
        val persistedStudent = studentDao.findByName(studentName)
        return nl.openvalue.meetup.testcontainers.demo.api.Student(
            persistedStudent.name,
            persistedStudent.yearOfRegistration,
            persistedStudent.mainCourse
        )
    }
}