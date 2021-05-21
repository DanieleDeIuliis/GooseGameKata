class InMemoryPlayer: PlayerRepository {

    private val players = mutableListOf<String>()

    override fun add(player: String): Boolean {
        if(players.contains(player)) {
            return false
        }
        players.add(player)
        return true
    }

    override fun all(): List<String> {
        return players
    }
}
