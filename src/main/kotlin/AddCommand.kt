class AddCommand(private val command: String, private val playerRepository: PlayerRepository) : Command {
    override fun exec(): String {
        val playerName = command.split(" ").last()
        return if(playerRepository.add(playerName)) {
            "Players: ${playerRepository.all().joinToString(", ")}"
        } else {
            "$playerName: already existing player"
        }
    }

}