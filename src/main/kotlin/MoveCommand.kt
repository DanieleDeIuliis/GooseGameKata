class MoveCommand(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData) :
    Command {
    override fun exec(): String {
        commandData.apply {
            val movement = movePlayer()
            repository.updatePositionOf(playerName, movement.finalPosition)
            return message(movement)
        }
    }

    private fun movePlayer(): Movement {
        val startingPosition = repository.positionOf(commandData.playerName)
        var finalPosition = commandData.firstDiceRoll + commandData.secondDiceRoll + startingPosition
        var bouncedBack = 0
        if(finalPosition > 63) {
            bouncedBack = finalPosition - 63
            finalPosition = 63 - bouncedBack
        }
        return Movement(startingPosition, finalPosition, bouncedBack)
    }

    private fun message(movement: Movement): String {
        commandData.apply {
            var message = "$playerName rolls $firstDiceRoll, $secondDiceRoll. "

            message += if(movement.bounced > 0) {
                "$playerName moves from ${movement.startingPosition.toStartString()} to 63. $playerName bounces! $playerName returns to ${movement.finalPosition}"
            } else {
                "$playerName moves from ${movement.startingPosition.toStartString()} to ${movement.finalPosition}"
            }

            if (movement.finalPosition == 63) {
                message += ". $playerName Wins!!"
            }
            return message
        }
    }

    private fun Int.toStartString(): String = if (this == 0) "Start" else this.toString()
}

data class Movement(val startingPosition: Int, val finalPosition: Int, val bounced: Int)