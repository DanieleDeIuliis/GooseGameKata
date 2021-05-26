class CommandFactory(private val playerNameRepository: PlayerRepository, private val diceRoller: DiceRoller)  {
    fun build(command: String): Command {
        return when(command.split(" ").first()) {
            "add" -> AddCommand(playerNameRepository, buildAddData(command))
            "move" -> MoveCommand(playerNameRepository, buildMoveData(command))
            else -> TODO()
        }
    }

    private fun buildMoveData(command: String): MoveCommandData {
        val playerName = getPlayerNameFrom(command)
        val rolls = diceRoller.roll()
        return MoveCommandData(playerName, rolls.first, rolls.second)
    }

    private fun buildAddData(command: String): AddCommandData {
        val playerName = getPlayerNameFrom(command)
        return AddCommandData(playerName)
    }

    private fun getPlayerNameFrom(command: String) = command.split(" ").last()
}

