package nl.openvalue.meetup.testcontainers.demo.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import nl.openvalue.meetup.testcontainers.demo.services.StudentService
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class StudentApi(val studentService: StudentService) {

    @GetMapping("students/{name}")
    fun retrieveStudent(@PathVariable("name") name: String): ResponseEntity<Student> {
        val retrieveStudent = studentService.retrieveStudent(name)
        return if (retrieveStudent.isPresent){
            ok(retrieveStudent.get())
        } else {
            notFound().build()
        }
    }
}
