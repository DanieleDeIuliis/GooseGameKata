class CommandFactory(private val playerRepository: PlayerRepository)  {
    fun build(command: String): Command {
        return if (command.contains("add")) {
            AddCommand(command, playerRepository)
        } else {
            MoveCommand(command)
        }
    }
}