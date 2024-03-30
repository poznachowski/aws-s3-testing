package pl.poznachowski.awss3demo.infra

import org.springframework.context.annotation.Import
import org.springframework.test.context.TestExecutionListeners
import java.lang.annotation.Inherited

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Import(S3LocalStackContainerConfiguration::class)
@MustBeDocumented
@TestExecutionListeners(
    value = [S3CleanupTestExecutionListener::class],
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
annotation class EnableS3TestContainer(
    /**
     * The names of the buckets that should be created before each test. Supports SpEL.
     */
    val buckets: Array<String> = [],
)
