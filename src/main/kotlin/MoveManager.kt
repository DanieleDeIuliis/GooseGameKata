class MoveManager(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData) {
    fun movePlayer(): Movement {
        val startingPosition = repository.positionOf(commandData.playerName)
        var positionAfterDiceRoll = commandData.firstDiceRoll + commandData.secondDiceRoll + startingPosition
        val bridgeTaken = bridgeTaken(positionAfterDiceRoll)
        val bouncedBack = bouncedBackAmount(positionAfterDiceRoll)
        val finalPosition = computeFinalPositionFrom(positionAfterDiceRoll, bridgeTaken, bouncedBack)
        return Movement(startingPosition, finalPosition, bouncedBack, bridgeTaken)
    }

    private fun computeFinalPositionFrom(positionAfterDiceRoll: Int, bridgeTaken: Boolean, bouncedBack: Int): Int {
        if (bridgeTaken) {
            return 12
        }
        if (bouncedBack > 0) {
            return 63 - bouncedBack
        }
        return positionAfterDiceRoll
    }

    private fun bridgeTaken(positionAfterDiceRoll: Int): Boolean {
        return positionAfterDiceRoll == 6
    }

    private fun bouncedBackAmount(positionAfterDiceRoll: Int): Int {
        var bouncedBack = 0
        if (positionAfterDiceRoll > 63) {
            bouncedBack = positionAfterDiceRoll - 63
        }
        return bouncedBack
    }
}