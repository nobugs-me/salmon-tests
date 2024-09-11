package ph.salmon.test

import io.qameta.allure.AllureId
import io.qameta.allure.Issue
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostsTest: BaseApiTest() {

    private val urlSuffix = "/posts"
    private val allureFilter = AllureRestAssured()

    @BeforeAll
    fun setUp() {
        RestAssured.baseURI = BASE_URL
        RestAssured.basePath = urlSuffix
        RestAssured.filters(allureFilter)
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `get a post`() {
        val expectedPost = Post(
            id = 1,
            userId = 1,
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\n" +
                    "nostrum rerum est autem sunt rem eveniet architecto"
        )
        val actualResponse = RestAssured
            .get("/1")
            .then()
            .extract()
            .body()
            .`as`(Post::class.java)
        assertThat(actualResponse, equalTo(expectedPost))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `check posts amount`() {
        RestAssured
            .get()
            .then()
            .assertThat()
            .statusCode(200)
            .and()
            .body("", hasSize<Int>(100))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `create a post`() {
        val expectedPost = Post(101, 1, "foo", "bar")
        val actualPost = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(expectedPost)
            .post()
            .then()
            .assertThat()
            .statusCode(201)
            .and()
            .extract()
            .body()
            .`as`(Post::class.java)
        assertThat(actualPost, equalTo(expectedPost))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `update a post`() {
        val expectedPost = Post(1, 1, "1", "1")
        val actualPost = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(expectedPost)
            .put("/1")
            .then()
            .assertThat()
            .statusCode(200)
            .and()
            .extract()
            .body()
            .`as`(Post::class.java)
        assertThat(actualPost, equalTo(expectedPost))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `patch a post`() {
        val newTitle = mapOf("title" to "new title")
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(newTitle)
            .patch("/1")
            .then()
            .assertThat()
            .statusCode(200)
            .and()
            .body("title", equalTo(newTitle["title"]))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `delete a post`() {
        RestAssured
            .delete("/1")
            .then()
            .assertThat()
            .statusCode(200)
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `get posts filtered by user_id`() {
        RestAssured
            .given()
            .queryParam("userId", 1)
            .get()
            .then()
            .assertThat()
            .body("userId", everyItem(equalTo(1)))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    /**
     * Можно поиграться с аллюром, чтобы иметь в итоге в отчете структуру шагов по схеме GIVEN-WHEN-THEN
     * Привел для примера, все тесты уж не стал так делать)
     */
    fun `posts amount assertion with step annotation`() {
        lateinit var response: List<Post>
        WHEN("request posts") {
            response = RestAssured.get()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList("", Post::class.java)
        }
        THEN("assert the list size is 100") {
            assertThat(response, hasSize(100))
        }
    }
}
