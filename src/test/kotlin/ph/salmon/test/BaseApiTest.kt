package ph.salmon.test

import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

@Timeout(value = 20, unit = TimeUnit.SECONDS)
open class BaseApiTest {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}
