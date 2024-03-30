package pl.poznachowski.awss3demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.poznachowski.awss3demo.DoBucketMagic

@SpringBootTest
internal class IntegrationNoLocalStackTest @Autowired constructor(
    val doBucketMagic: DoBucketMagic
) {

    @Test
    fun abc() {
        doBucketMagic.store()
        assertThat(doBucketMagic.read()).isEqualTo("hey")
    }


}
