package ph.salmon.test

import io.qameta.allure.AllureId
import io.qameta.allure.Issue
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.concurrent.atomic.AtomicInteger

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordsCounterTest: BaseApiTest() {

    private val urlSuffix = "/posts"
    private val allureFilter = AllureRestAssured()

    @BeforeAll
    fun setUp() {
        RestAssured.baseURI = BASE_URL
        RestAssured.basePath = urlSuffix
        RestAssured.filters(allureFilter)
    }

    @Test
    @AllureId("some id")
    @Issue("some issue id")
    fun `count and print the most used words with gwt`() {
        lateinit var posts: List<Post>
        val wordsCounterMap = mutableMapOf<String, Int>()
        GIVEN("request all posts") {
            posts = RestAssured
                .get()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList("", Post::class.java)
        }
        WHEN("count the words in bodies") {
            posts.flatMap { it.body.split("\\s+".toRegex()) }
                .groupingBy { it }
                .eachCount()
                .let { wordsCounterMap.putAll(it) }
        }
        THEN("print the most used words") {
            val counter = AtomicInteger(1)
            wordsCounterMap.entries
                .sortedByDescending { it.value }
                .take(10)
                .forEach { pair ->
                    println("${counter.getAndIncrement()}. ${pair.key} - ${pair.value}")
                }
        }
    }

    @Test
    @AllureId("some id")
    @Issue("some issue id")
    /**
     * Если без выкрутасов с аллюром
     */
    fun `count and print the most used words`() {
        val counter = AtomicInteger(1)
        RestAssured
            .get()
            .then()
            .extract()
            .body()
            .jsonPath()
            .getList("", Post::class.java)
            .flatMap { it.body.split("\\s+".toRegex()) }
            .groupingBy { it }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .take(10)
            .forEach { pair ->
                println("${counter.getAndIncrement()}. ${pair.key} - ${pair.value}")
            }
    }
}
