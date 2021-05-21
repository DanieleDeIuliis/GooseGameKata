class MoveCommand(private val command: String): Command {
    override fun exec(): String {
        val nameAndDiceResults = command.split("move").last().trim().split(" ")
        val playerName = nameAndDiceResults.first()
        val firstRoll = nameAndDiceResults[1].trim(',')
        val secondRoll = nameAndDiceResults[2]
        val diceValue = firstRoll.toInt() + secondRoll.toInt()
        return "$playerName rolls, $firstRoll, $secondRoll. $playerName moves from Start to $diceValue"
    }
}