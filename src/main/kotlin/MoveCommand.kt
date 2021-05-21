class MoveCommand(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData): Command {
    override fun exec(): String {
        val movement = movePlayer()
        repository.updatePositionOf(commandData.playerName, movement.finalPosition)
        return MessageBuilder(movement, commandData).build()
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
}

