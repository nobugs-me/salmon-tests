package ph.salmon.test

import io.qameta.allure.AllureId
import io.qameta.allure.Issue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import ph.salmon.test.generators.TestDataGenerator.generate
import ph.salmon.test.models.Post
import ph.salmon.test.requests.PostService
import ph.salmon.test.specs.unauthSpec

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostsTest: BaseApiTest() {
    private lateinit var postService: PostService
    private lateinit var expectedPost: Post

    @BeforeEach
    fun setupTestData() {
        postService = PostService(unauthSpec())
        expectedPost = generate(Post::class.java)
    }

    @AfterEach
    fun cleanTestData() {
        postService.allCreatedPosts.forEach { postService.delete(it) }
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `get a post`() {
        val actualResponse = postService.read(expectedPost.id)

        // + ассерт по всей сущности
        // - нет софт ассертов
        assertThat(actualResponse, equalTo(expectedPost))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `create a post`() {
        val actualPost = postService.create(expectedPost)
        // - снова хард ассерты
        assertThat(actualPost, equalTo(expectedPost))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `update a post`() {
        val actualPost = postService.update(expectedPost.id, expectedPost)
        assertThat(actualPost, equalTo(expectedPost))
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    fun `delete a post`() {
        postService.delete(expectedPost.id)
    }
}
