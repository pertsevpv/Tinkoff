class PostData() {
    private val postList: List<Post> = listOf<Post>(
        Post(0, "Title0", "Body0"),
        Post(1, "Title1", "Body1"),
        Post(2, "Title2", "Body2"),
        Post(3, "Title3", "Body3"),
        Post(4, "Title4", "Body4"),
        Post(5, "Title5", "Body5"),
        Post(6, "Title6", "Body6"),
        Post(7, "Title7", "Body7"),
        Post(8, "Title8", "Body8"),
        Post(9, "Title9", "Body9"),
        Post(10, "Title10", "Body10"),
        Post(11, "Title11", "Body11"),
        Post(12, "Title12", "Body12"),
        Post(13, "Title13", "Body13"),
        Post(14, "Title14", "Body14"),
        Post(15, "Title15", "Body15"),
        Post(16, "Title16", "Body16"),
        Post(17, "Title17", "Body17"),
        Post(18, "Title18", "Body18"),
        Post(19, "Title19", "Body19"),
        Post(20, "Title20", "Body20"),
        Post(21, "Title21", "Body21"),
        Post(22, "Title22", "Body22"),
        Post(23, "Title23", "Body23"),
        Post(24, "Title24", "Body24"),
        Post(25, "Title25", "Body25"),
        Post(26, "Title26", "Body26"),
        Post(27, "Title27", "Body27"),
        Post(28, "Title28", "Body28"),
        Post(29, "Title29", "Body29"),
        Post(30, "Title30", "Body30"),
        Post(31, "Title31", "Body31"),
        Post(32, "Title32", "Body32"),
        Post(33, "Title33", "Body33"),
        Post(34, "Title34", "Body34"),
        Post(35, "Title35", "Body35"),
        Post(36, "Title36", "Body36"),
        Post(37, "Title37", "Body37"),
        Post(38, "Title38", "Body38"),
        Post(39, "Title39", "Body39"),
        Post(40, "Title40", "Body40"),
        Post(41, "Title41", "Body41"),
        Post(42, "Title42", "Body42"),
        Post(43, "Title43", "Body43"),
        Post(44, "Title44", "Body44"),
        Post(45, "Title45", "Body45"),
        Post(46, "Title46", "Body46"),
        Post(47, "Title47", "Body47"),
        Post(48, "Title48", "Body48"),
        Post(49, "Title49", "Body49"),
        Post(50, "Title50", "Body50"),
        Post(51, "Title51", "Body51")
    )

    private val postsIDList = listOf(
        UserPosts(0, listOf(0, 1)),
        UserPosts(1, listOf(2, 3)),
        UserPosts(2, listOf(4, 5)),
        UserPosts(3, listOf(6, 7)),
        UserPosts(4, listOf(8, 9)),
        UserPosts(5, listOf(10, 11)),
        UserPosts(6, listOf(12, 13)),
        UserPosts(7, listOf(14, 15)),
        UserPosts(8, listOf(16, 17)),
        UserPosts(9, listOf(18, 19)),
        UserPosts(10, listOf(20, 21)),
        UserPosts(11, listOf(22, 23)),
        UserPosts(12, listOf(24, 25)),
        UserPosts(13, listOf(26, 27)),
        UserPosts(14, listOf(28, 29)),
        UserPosts(15, listOf(30, 31)),
        UserPosts(16, listOf(32, 33)),
        UserPosts(17, listOf(34, 35)),
        UserPosts(18, listOf(36, 37)),
        UserPosts(19, listOf(38, 39)),
        UserPosts(20, listOf(40, 41)),
        UserPosts(21, listOf(42, 43)),
        UserPosts(22, listOf(44, 45)),
        UserPosts(23, listOf(46, 47)),
        UserPosts(24, listOf(48, 49)),
        UserPosts(25, listOf(50, 51))
    )

    fun postData() = postsIDList

    fun getUserPostsByID(id: Int) = postData().find { it.userID == id }
}