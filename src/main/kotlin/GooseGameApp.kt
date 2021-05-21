class GooseGameApp(private val outputStream: OutputPrinter) {

    private val players: MutableList<String> = mutableListOf()

    fun exec(command: String) {
        if(command.contains("add")) {
            val palyerName = command.split(" ").last()
            players.add(palyerName)
            outputStream.printLine("Players: ${players.joinToString(", ")}")
        }
    }

}
