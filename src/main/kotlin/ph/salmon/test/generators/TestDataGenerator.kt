package ph.salmon.test.generators

import kotlin.random.Random
import kotlin.reflect.full.primaryConstructor

object TestDataGenerator {
    fun <T : Any> generate(clazz: Class<T>): T {
        val constructor = clazz.kotlin.primaryConstructor ?: return throw Exception("Cannot generate data")

        val args = constructor.parameters.map { param ->
            when (param.type.classifier) {
                Int::class -> Random.nextInt(1, 100)
                String::class -> generateRandomString(10)
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }

        return constructor.call(*args.toTypedArray())
    }

    private fun generateRandomString(length: Int): String {
        val allowedChars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}
