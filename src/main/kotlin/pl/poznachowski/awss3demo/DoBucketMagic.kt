package pl.poznachowski.awss3demo

import io.awspring.cloud.s3.S3Template
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream

const val BUCKET_NAME = "elo"

@Component
class DoBucketMagic(
    private val template: S3Template
) {

    fun store() {
        if (!template.bucketExists(BUCKET_NAME)) {
            template.createBucket(BUCKET_NAME)
        }
        template.upload(BUCKET_NAME, "test.txt", ByteArrayInputStream("hey".encodeToByteArray()))
    }

    fun read() : String {
        return template.download(BUCKET_NAME, "test.txt").inputStream.bufferedReader().use {
            it.readText()
        }
    }

}
