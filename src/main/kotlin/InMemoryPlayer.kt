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

    override fun updatePositionOf(player: String, position: Int) {
        TODO("Not yet implemented")
    }

    override fun positionOf(player: String): Int {
        TODO("Not yet implemented")
    }
}
