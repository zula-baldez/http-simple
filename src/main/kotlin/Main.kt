

fun main(args: Array<String>) {
    val au = AuthorizationHandler()
    val test1 = au.loginAndGetApCookie("123456", "EBLAN")
    println(test1)
    val infoHandler = InfoHandler("ISU_AP_COOKIE=$test1")
    infoHandler.checkInstance()
    val res2 = infoHandler.getFreeRooms(InfoHandler.Place.KRONVA, "15:00-15:30", "25.04.2023", InfoHandler.Type.MEETING_ROOM)

    println(res2)
    return
}