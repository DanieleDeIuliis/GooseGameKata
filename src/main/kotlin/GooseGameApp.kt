class GooseGameApp(private val outputStream: OutputPrinter, val playerRepository: PlayerRepository) {

    fun exec(command: String) {
        if(command.contains("add")) {
            addPlayer(command)
        }
    }

    private fun addPlayer(command: String) {
        val playerName = command.split(" ").last()
        if(playerRepository.add(playerName)) {
            outputStream.printLine("Players: ${playerRepository.all().joinToString(", ")}")
        } else {
            outputStream.printLine("$playerName: already existing player")
        }
    }

}
