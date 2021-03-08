import java.lang.StringBuilder

data class UserAndPosts(
    val userID: Int,
    val name: String,
    val surname: String,
    val email: String,
    val phone: Int,
    val postsID: List<Int>
){
    override fun toString(): String =
        StringBuilder().apply {
            append("{")
            append("id: ").append(userID).append(", ")
            append(name).append(" ").append(surname).append(", ")
            append("email: ").append(email).append(", ")
            append("tel: ").append(phone).append(", ")
            append("posts: ").append(postsID)
            append("}")
        }.toString()
}