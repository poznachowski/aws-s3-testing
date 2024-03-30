package pl.poznachowski.awss3demo.infra

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class S3LocalStackContainerConfiguration {

    @Bean
    @ServiceConnection
    fun localStackContainer(): LocalStackContainer {
        return LocalStackContainer(DockerImageName.parse("localstack/localstack:3.2"))
            .withReuse(true)
            .withServices(LocalStackContainer.Service.S3)
    }

}
