package pl.poznachowski.awss3demo.infra

import org.springframework.test.context.TestContext
import org.springframework.test.context.TestContextAnnotationUtils
import org.springframework.test.context.TestExecutionListener
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ListObjectsRequest

class S3CleanupTestExecutionListener : TestExecutionListener {

    override fun beforeTestMethod(testContext: TestContext) {
        val s3Client = testContext.getBean(S3Client::class.java)
        removeAllBuckets(s3Client)
        testContext.getRestAssuredSupport()?.let { annotation ->
            annotation.buckets
                .map { bucket -> testContext.applicationContext.environment.resolvePlaceholders(bucket) }
                .forEach {
                    println("Creating bucket: $it")
                    s3Client.createBucket { req -> req.bucket(it) }
                }
        }
    }

    private fun removeAllBuckets(s3Client: S3Client) {
        s3Client.listBuckets().buckets().map { it.name() }.forEach { bucket ->
            println("Removing bucket: $bucket")
            s3Client.listObjects(ListObjectsRequest.builder().bucket(bucket).build()).contents()
                .forEach { s3Object ->
                    s3Client.deleteObject { it.bucket(bucket).key(s3Object.key()) }
                }
            s3Client.deleteBucket { it.bucket(bucket) }
        }
    }

    private fun <T> TestContext.getBean(beanClass: Class<T>): T {
        return this.applicationContext.getBean(beanClass)
    }

    private fun TestContext.getRestAssuredSupport(): EnableS3TestContainer? {
        return TestContextAnnotationUtils.findMergedAnnotation(this.testClass, EnableS3TestContainer::class.java)
    }
}
