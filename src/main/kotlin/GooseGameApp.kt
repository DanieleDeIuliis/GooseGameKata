class GooseGameApp(private val outputStream: OutputPrinter) {

    private val players: MutableList<String> = mutableListOf()

    fun exec(command: String) {
        if(command.contains("add")) {
            addPlayer(command)
        }
    }

    private fun addPlayer(command: String) {
        val playerName = command.split(" ").last()
        players.add(playerName)
        outputStream.printLine("Players: ${players.joinToString(", ")}")
    }

}
