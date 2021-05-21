class GooseGameApp(private val outputStream: OutputPrinter, val playerRepository: PlayerRepository) {

    fun exec(command: String) {
        if(command.contains("add")) {
            addPlayer(command)
        } else if(command.contains("move")) {
            movePlayer(command)
        }
    }

    private fun movePlayer(command: String) {
        val nameAndDiceResults = command.split("move").last().trim().split(" ")
        val playerName = nameAndDiceResults.first()
        val firstRoll = nameAndDiceResults[1].trim(',')
        val secondRoll = nameAndDiceResults[2]
        val diceValue = firstRoll.toInt() + secondRoll.toInt()
        outputStream.printLine("$playerName rolls, $firstRoll, $secondRoll. $playerName moves from Start to $diceValue")
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
