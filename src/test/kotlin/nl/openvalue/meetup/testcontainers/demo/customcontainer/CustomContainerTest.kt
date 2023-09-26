package nl.openvalue.meetup.testcontainers.demo.customcontainer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class CustomContainerTest {

    @Container
    private val bashContainer = GenericContainer("bash:devel-alpine3.18")
        .withCommand("echo Hello World!")

    @Test
    fun `test hello world`() {
        assertThat(bashContainer.logs).contains("Hello World!")
    }
}