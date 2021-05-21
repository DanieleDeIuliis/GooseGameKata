class CommandFactory(private val playerNameRepository: PlayerRepository)  {
    fun build(command: String): Command {
        return if (command.contains("add")) {
            AddCommand(command, playerNameRepository)
        } else {
            MoveCommand(command, playerNameRepository)
        }
    }
}