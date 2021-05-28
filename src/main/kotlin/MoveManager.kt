class MoveManager(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData) {
     fun movePlayer(): Movement {
        val startingPosition = repository.positionOf(commandData.playerName)
        var finalPosition = commandData.firstDiceRoll + commandData.secondDiceRoll + startingPosition
        var bridgeTaken = false
        if(finalPosition == 6) {
            finalPosition = 12
            bridgeTaken = true
        }
        var bouncedBack = 0
        if(finalPosition > 63) {
            bouncedBack = finalPosition - 63
            finalPosition = 63 - bouncedBack
        }
        return Movement(startingPosition, finalPosition, bouncedBack, bridgeTaken)
    }
}