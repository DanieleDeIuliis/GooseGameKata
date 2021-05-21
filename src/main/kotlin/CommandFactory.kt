class CommandFactory(private val playerNameRepository: PlayerRepository)  {
    fun build(command: String): Command {
        return when(command.split(" ").first()) {
            "add" -> AddCommand(playerNameRepository, buildAddData(command))
            "move" -> MoveCommand(playerNameRepository, buildMoveData(command))
            else -> TODO()
        }
    }

    private fun buildMoveData(command: String): MoveCommandData {
        val playerName = command.replace("move ", "").split(" ").first()
        val firstDiceRoll = command.replace("move ", "").split(" ")[1].replace(",", "").toInt()
        val secondDiceRoll = command.replace("move ", "").split(" ")[2].toInt()
        return MoveCommandData(playerName, firstDiceRoll, secondDiceRoll)
    }

    private fun buildAddData(command: String): AddCommandData {
        val playerName = command.split(" ").last()
        return AddCommandData(playerName)
    }
}

