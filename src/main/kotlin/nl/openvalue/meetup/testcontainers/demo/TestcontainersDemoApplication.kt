package nl.openvalue.meetup.testcontainers.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestcontainersDemoApplication

fun main(args: Array<String>) {
    runApplication<TestcontainersDemoApplication>(*args)
}
