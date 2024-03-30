package pl.poznachowski.awss3demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AwsS3DemoApplication

fun main(args: Array<String>) {
    runApplication<AwsS3DemoApplication>(*args)
}
