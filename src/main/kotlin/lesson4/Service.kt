package lesson4

class Service {

    fun f5a(): List<UserAndPosts> =
        UserData().userData().map { user ->
            UserAndPosts(
                user.userID,
                user.name,
                user.surname,
                user.email,
                user.phone,
                PostData().getUserPostsByID(user.userID)!!.postsID
            )
        }

    fun f5b(): List<UserAndPosts> =
        f5a().sortedBy { it.name }

    fun f5c(): Map<String, List<UserAndPosts>> =
        f5a().groupBy { it.email.substring(it.email.indexOf('@')) }

    fun f5d(): Int =
        f5a().count { it.name[0] == it.surname[0] }
}