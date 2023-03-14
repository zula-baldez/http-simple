

fun main(args: Array<String>) {
    val infoHandler : InfoHandler = InfoHandler;


     infoHandler.checkInstance()
    val ans =infoHandler.getFreeRooms(InfoHandler.Place.KRONVA, "10:00-10:30", "25.05.2023", InfoHandler.Type.MEETING_ROOM)
    println(ans)

    return
}