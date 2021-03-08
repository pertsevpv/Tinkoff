class Service {

    fun f5a() =
        UserData().userData().map { it ->
            UserAndPosts(
                it.userID,
                it.name,
                it.surname,
                it.email,
                it.phone,
                PostData().getUserPostsByID(it.userID)!!.postsID
            )
        }

    fun f5b() =
        f5a().sortedBy { it.name }

    fun f5c() =
        f5a().groupBy { it.email.substring(it.email.indexOf('@')) }

    fun f5d() = f5a().filter { it.name[0] == it.surname[0] }.size
}