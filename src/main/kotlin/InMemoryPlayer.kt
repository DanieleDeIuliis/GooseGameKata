class InMemoryPlayer: PlayerRepository {

    private val players = mutableListOf<String>()
    private val players2 = mutableListOf<Player>()

    override fun add(playerName: String): Boolean {
        if(players.contains(playerName)) {
            return false
        }
        players.add(playerName)
        players2.add(Player(playerName, 0))
        return true
    }

    override fun all(): List<String> {
        return players
    }

    override fun updatePositionOf(player: String, position: Int) {
        TODO("Not yet implemented")
    }

    override fun positionOf(player: String): Int {
        return players2.single { p -> p.name == player }.position
    }

    data class Player(val name: String, val position: Int)
}
