package ph.salmon.test.requests

import io.restassured.RestAssured.given
import io.restassured.specification.RequestSpecification
import org.apache.http.HttpStatus
import ph.salmon.test.models.Post

class PostService(val reqSpec: RequestSpecification) : CrudInterface<Post>  {
    private val POSTS_URL = "/posts"
    private val posts = mutableMapOf<Int, Post>()
    val allPosts: Map<Int, Post> get() = posts.toMap()

    override fun create(item: Post): Post {
        val post = given()
            .spec(reqSpec)
            .body(item)
            .post(POSTS_URL)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .body()
            .`as`(Post::class.java)
        posts.put(post.id, post)
        return post
    }

    override fun read(id: Int): Post {
        return given()
            .spec(reqSpec)
            .get("$POSTS_URL/$id")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(Post::class.java)
    }

    override fun delete(id: Int): String {
        return given()
            .spec(reqSpec)
            .delete("$POSTS_URL/$id")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .extract()
            .body()
            .asString()
    }

    override fun update(id: Int, item: Post): Post {
        return given()
            .spec(reqSpec)
            .body(item)
            .put("$POSTS_URL/$id")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .extract()
            .body()
            .`as`(Post::class.java)
    }
}