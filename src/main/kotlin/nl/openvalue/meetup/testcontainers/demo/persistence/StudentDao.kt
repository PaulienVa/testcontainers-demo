package nl.openvalue.meetup.testcontainers.demo.persistence

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StudentDao : JpaRepository<Student, Long> {
    fun findByName(name: String) : Optional<Student>
}

@Entity(name = "students")
class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    val studentId: Long? = null,
    val name: String,
    val yearOfRegistration: Int,
    val mainCourse: String
)