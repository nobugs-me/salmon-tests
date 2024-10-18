package ph.salmon.test

import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

@Timeout(value = 20, unit = TimeUnit.SECONDS)
open class BaseApiTest {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"

        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            RestAssured.baseURI = BASE_URL
            RestAssured.filters(
                RequestLoggingFilter(), ResponseLoggingFilter(),
                AllureRestAssured()
            )
        }
    }

}
