class AddCommand(private val playerNameRepository: PlayerNameRepository, private val data: AddCommandData) : Command {
    override fun exec(): String {
        val playerName = data.playerName
        return if(playerNameRepository.add(playerName)) {
            "Players: ${playerNameRepository.all().joinToString(", ")}"
        } else {
            "$playerName: already existing player"
        }
    }

}