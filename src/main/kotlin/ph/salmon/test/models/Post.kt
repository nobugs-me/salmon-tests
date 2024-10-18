package ph.salmon.test.models

data class Post(
    var id: Int,
    val userId: Int,
    val title: String,
    val body: String
)
