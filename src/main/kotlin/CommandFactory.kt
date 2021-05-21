class CommandFactory  {
    fun build(command: String, playerRepository: PlayerRepository): Command {
        return if (command.contains("add")) {
            AddCommand(command, playerRepository)
        } else {
            MoveCommand(command)
        }
    }
}