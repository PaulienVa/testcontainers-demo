package nl.openvalue.meetup.testcontainers.demo.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Repository
interface StudentDao : JpaRepository<Student, Long> {
    fun findByName(name: String) : Student
}

@Entity(name = "students")
class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val studentId: Long? = null,
    val name: String,
    val yearOfRegistration: Number,
    val mainCourse: String
)