class MoveCommand(private val repository: PlayerPositionRepository, private val commandData: MoveCommandData): Command {
    override fun exec(): String {
        val movement = MoveManager(repository, commandData).movePlayer()
        repository.updatePositionOf(commandData.playerName, movement.finalPosition)
        return MessageBuilder(movement, commandData).build()
    }
}

