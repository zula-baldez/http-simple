fun main(args: Array<String>) {
    val infoHandler : InfoHandler = InfoHandler;
    val ans = infoHandler.getRoomInfo(20007, "25.03.2023")
    println(ans.schedule)
    return
}