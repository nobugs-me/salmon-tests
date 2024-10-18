package ph.salmon.test.requests

import io.restassured.RestAssured.given
import io.restassured.specification.RequestSpecification
import org.apache.http.HttpStatus
import ph.salmon.test.models.Post

class PostService(val reqSpec: RequestSpecification) : CrudInterface<Post> {
    private val createdPosts: MutableList<Int> = mutableListOf()
    val allCreatedPosts: List<Int> get() = createdPosts

    override fun create(item: Post): Post {
        val createdPost = given()
            .spec(reqSpec)
            .body(item)
            .put(POSTS_URL)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract().body()
            .`as`(Post::class.java)
        createdPosts.add(createdPost.id)
        return createdPost
    }

    override fun read(id: Int): Post {
        return given()
            .spec(reqSpec)
            .get("$POSTS_URL/$id")
            .then()
            .extract()
            .body()
            .`as`(Post::class.java)
    }

    override fun delete(id: Int): String {
        return given()
            .spec(reqSpec)
            .put("$POSTS_URL/$id")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_NO_CONTENT)
            .extract().body()
            .asString()
    }

    override fun update(id: Int, item: Post): Post? {
        return given()
            .spec(reqSpec)
            .body(item)
            .put("$POSTS_URL/$id")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract().body()
            .`as`(Post::class.java)
    }

    companion object {
        const val POSTS_URL = "/posts"
    }
}