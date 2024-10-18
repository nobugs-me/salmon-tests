package ph.salmon.test.specs

import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification


fun unauthSpec(): RequestSpecification {
    val reqSpecBuilder = RequestSpecBuilder()
    reqSpecBuilder.setContentType(ContentType.JSON)
    return reqSpecBuilder.build()
}
