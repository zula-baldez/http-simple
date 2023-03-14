

fun main(args: Array<String>) {
    val infoHandler : InfoHandler = InfoHandler;


    val ans = infoHandler.getFreeRooms(InfoHandler.Place.KRONVA, "17:00-17:30", "21.03.2023", InfoHandler.Type.MEETING_ROOM)
    println(ans)

    return
}