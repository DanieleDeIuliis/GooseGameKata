class MessageBuilder(private val movement: Movement, private val commandData: MoveCommandData) {
    fun build(): String {
        return  messageInit(movement.startingPosition)
            .handleBridge()
            .handleBounce(movement.bounced)
            .addFinalPosition(movement.finalPosition)
            .handleVictory(movement.finalPosition)
    }

    private fun messageInit(startingPosition: Int): String {
        return "${commandData.playerName} rolls ${commandData.firstDiceRoll}, ${commandData.secondDiceRoll}. " +
                "${commandData.playerName} moves from ${startingPosition.toStartString()} to"
    }

    private fun String.handleBridge(): String {
        if(movement.bridgeTaken) {
            return this + " The Bridge. ${commandData.playerName} jumps to"
        }
        return this
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