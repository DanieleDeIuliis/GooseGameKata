class MoveCommand(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData) :
    Command {
    override fun exec(): String {
        commandData.apply {
            val startingPosition = repository.positionOf(playerName)
            val finalPosition = firstDiceRoll + secondDiceRoll + startingPosition
            if(finalPosition > 63) {
                val newFinalPosition = 63 - (finalPosition - 63)
                repository.updatePositionOf(playerName, newFinalPosition)
                return message(startingPosition, newFinalPosition, true)
            } else {
                repository.updatePositionOf(playerName, finalPosition)
                return message(startingPosition, finalPosition)
            }
        }
    }

    private fun message(startingPosition: Int, finalPosition: Int, hasBouncedBack: Boolean = false): String {
        commandData.apply {
            var message = "$playerName rolls $firstDiceRoll, $secondDiceRoll. "

            message += if(hasBouncedBack) {
                "$playerName moves from ${startingPosition.toStartString()} to 63. $playerName bounces! $playerName returns to $finalPosition"
            } else {
                "$playerName moves from ${startingPosition.toStartString()} to $finalPosition"
            }

            if (finalPosition == 63) {
                message += ". $playerName Wins!!"
            }
            return message
        }
    }

    private fun Int.toStartString(): String = if (this == 0) "Start" else this.toString()
}