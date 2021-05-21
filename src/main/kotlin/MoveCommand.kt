import java.lang.StringBuilder

class MoveCommand(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData) :
    Command {
    override fun exec(): String {
        val movement = movePlayer()
        repository.updatePositionOf(commandData.playerName, movement.finalPosition)
        return message(movement)
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
        return "${commandData.playerName} rolls ${commandData.firstDiceRoll}, ${commandData.secondDiceRoll}. " +
                "${commandData.playerName} moves from ${movement.startingPosition.toStartString()} to"
                    .handleBounce(movement.bounced)
                    .addFinalPosition(movement.finalPosition)
                    .handleVictory(movement.finalPosition)
    }

    private fun String.handleBounce(bounced: Int): String {
        if(bounced > 0) {
            return this + " 63. ${commandData.playerName} bounces! ${commandData.playerName} returns to"
        }
        return this
    }

    private fun String.addFinalPosition(finalPosition: Int): String = "$this $finalPosition"

    private fun String.handleVictory(finalPosition: Int): String {
        if(finalPosition == 63) {
            return this + ". ${commandData.playerName} Wins!!"
        }
        return this
    }

    private fun Int.toStartString(): String = if (this == 0) "Start" else this.toString()
}

data class Movement(val startingPosition: Int, val finalPosition: Int, val bounced: Int)