data class UserAndPosts(
    val userID: Int,
    val name: String,
    val surname: String,
    val email: String,
    val phone: Int,
    val postsID: List<Int>
)