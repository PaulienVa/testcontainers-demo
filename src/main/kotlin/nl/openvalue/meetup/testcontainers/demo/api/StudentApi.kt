package nl.openvalue.meetup.testcontainers.demo.api

import nl.openvalue.meetup.testcontainers.demo.services.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class StudentApi(val studentService: StudentService) {

    @GetMapping("students/{name}")
    fun retrieveStudent(@PathVariable("name") name: String): ResponseEntity<Student> {
        return ok(studentService.retrieveStudent(name))
    }
}