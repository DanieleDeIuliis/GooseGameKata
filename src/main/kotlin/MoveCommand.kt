class MoveCommand(private val command: String, private val repository: PlayerPositionRepository): Command {
    override fun exec(): String {
        val nameAndDiceResults = command.split("move").last().trim().split(" ")
        val playerName = nameAndDiceResults.first()
        val firstRoll = nameAndDiceResults[1].trim(',')
        val secondRoll = nameAndDiceResults[2]
        val startingPosition = repository.positionOf(playerName)
        val finalPosition = firstRoll.toInt() + secondRoll.toInt() + startingPosition
        repository.updatePositionOf(playerName, finalPosition)
        if(startingPosition == 0) {
            return "$playerName rolls, $firstRoll, $secondRoll. $playerName moves from Start to $finalPosition"
        } else {
            return "$playerName rolls, $firstRoll, $secondRoll. $playerName moves from $startingPosition to $finalPosition"
        }
    }
}