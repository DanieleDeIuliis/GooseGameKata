class MoveCommand(
    private val repository: PlayerPositionRepository, private val commandData: MoveCommandData): Command {
    override fun exec(): String {
        val startingPosition = repository.positionOf(commandData.playerName)
        val finalPosition = commandData.firstDiceRoll + commandData.secondDiceRoll + startingPosition
        repository.updatePositionOf(commandData.playerName, finalPosition)
        return if(startingPosition == 0) {
            "${commandData.playerName} rolls, ${commandData.firstDiceRoll}, ${commandData.secondDiceRoll}. ${commandData.playerName} moves from Start to $finalPosition"
        } else {
            "${commandData.playerName} rolls, ${commandData.firstDiceRoll}, ${commandData.secondDiceRoll}. ${commandData.playerName} moves from $startingPosition to $finalPosition"
        }
    }
}