package nl.openvalue.meetup.testcontainers.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class TestcontainersDemoApplication

fun main(args: Array<String>) {
    runApplication<TestcontainersDemoApplication>(*args)
}


@Bean
fun objectMapper(): ObjectMapper {
    return ObjectMapper().registerModule(KotlinModule.Builder().build())
}
