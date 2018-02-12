package io.mysocialapp.app.customfield

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["io.mysocialapp"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
