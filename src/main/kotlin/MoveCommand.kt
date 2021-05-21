class MoveCommand(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData) : Command {
    override fun exec(): String {
        commandData.apply {
            val startingPosition = repository.positionOf(playerName)
            val finalPosition = firstDiceRoll + secondDiceRoll + startingPosition
            repository.updatePositionOf(playerName, finalPosition)

            val message = "$playerName rolls $firstDiceRoll, $secondDiceRoll. " +
                    "$playerName moves from ${startingPosition.toStartString()} to $finalPosition"

            return if(finalPosition == 63) "$message. $playerName Wins!!" else message
        }
    }

    private fun Int.toStartString(): String = if (this == 0) "Start" else this.toString()
}