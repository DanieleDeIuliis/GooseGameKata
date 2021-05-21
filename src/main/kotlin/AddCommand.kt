class AddCommand(private val command: String, private val playerNameRepository: PlayerNameRepository) : Command {
    override fun exec(): String {
        val playerName = command.split(" ").last()
        return if(playerNameRepository.add(playerName)) {
            "Players: ${playerNameRepository.all().joinToString(", ")}"
        } else {
            "$playerName: already existing player"
        }
    }

}