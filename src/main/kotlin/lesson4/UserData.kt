package lesson4

class UserData {
    private val userList: List<User> = listOf(
        User(0, "Vitalya", "Aksyonov", "VitalyaAksyonov_736190@ya.ru", 736190),
        User(1, "Maxim", "Sidorov", "MaximSidorov_634526@example.vom", 634526),
        User(2, "Kolya", "Bagrov", "KolyaBagrov_582173@ya.ru", 582173),
        User(3, "Petr", "Ivanov", "PetrIvanov_925398@example.vom", 925398),
        User(4, "Maxim", "Aliyev", "MaximAliyev_135492@gmail.com", 135492),
        User(5, "Josef", "Petrov", "JosefPetrov_726765@ya.ru", 726765),
        User(6, "Andrew", "Abakumov", "AndrewAbakumov_688598@ya.ru", 688598),
        User(7, "Maxim", "Abakumov", "MaximAbakumov_180784@mail.ru", 180784),
        User(8, "Maxim", "Sidorov", "MaximSidorov_587352@gmail.com", 587352),
        User(9, "Ivan", "Petrov", "IvanPetrov_181754@gmail.com", 181754),
        User(10, "Ivan", "Ivanov", "IvanIvanov_315625@gmail.com", 315625),
        User(11, "Josef", "Ivanov", "JosefIvanov_921578@ya.ru", 921578),
        User(12, "Vitalya", "Bagrov", "VitalyaBagrov_812881@ya.ru", 812881),
        User(13, "Ivan", "Aliyev", "IvanAliyev_397892@outlook.com", 397892),
        User(14, "Ivan", "Bagrov", "IvanBagrov_659686@mail.ru", 659686),
        User(15, "Petr", "Belov", "PetrBelov_502155@example.vom", 502155),
        User(16, "Ivan", "Aliyev", "IvanAliyev_202318@example.vom", 202318),
        User(17, "Kolya", "Abakumov", "KolyaAbakumov_670020@example.vom", 670020),
        User(18, "Alex", "Aksyonov", "AlexAksyonov_334473@mail.ru", 334473),
        User(19, "Vitalya", "Ivanov", "VitalyaIvanov_521205@example.vom", 521205),
        User(20, "Alex", "Abakumov", "AlexAbakumov_996094@outlook.com", 996094),
        User(21, "Petr", "Aksyonov", "PetrAksyonov_113032@gmail.com", 113032),
        User(22, "Kolya", "Petrov", "KolyaPetrov_572509@mail.ru", 572509),
        User(23, "Josef", "Aliyev", "JosefAliyev_168664@ya.ru", 168664),
        User(24, "Kolya", "Petrov", "KolyaPetrov_889998@example.vom", 889998),
        User(25, "Ivan", "Petrov", "IvanPetrov_391333@outlook.com", 391333)
    )

    fun userData(): List<User> =
        userList

    fun getUserByID(id: Int): User? =
        userList.find { it.userID == id }
}