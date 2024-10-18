package ph.salmon.test.specs

import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification

fun unauthReqSpec(): RequestSpecification {
    val reqBuilder = RequestSpecBuilder()
    reqBuilder.setContentType(ContentType.JSON)
    return reqBuilder.build()
}

