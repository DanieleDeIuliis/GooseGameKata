class MoveCommand(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData) :
    Command {
    override fun exec(): String {
        commandData.apply {
            val startingPosition = repository.positionOf(playerName)
            val finalPosition = firstDiceRoll + secondDiceRoll + startingPosition
            repository.updatePositionOf(playerName, finalPosition)

            return message(startingPosition, finalPosition)
        }
    }

    private fun message(startingPosition: Int, finalPosition: Int): String {
        commandData.apply {
            var message = "$playerName rolls $firstDiceRoll, $secondDiceRoll. " +
                    "$playerName moves from ${startingPosition.toStartString()} to $finalPosition"

            if (finalPosition == 63) {
                message += ". $playerName Wins!!"
            }
            return message
        }
    }

    private fun Int.toStartString(): String = if (this == 0) "Start" else this.toString()
}