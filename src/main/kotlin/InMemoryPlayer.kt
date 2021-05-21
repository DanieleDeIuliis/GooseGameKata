class InMemoryPlayer: PlayerRepository {

    private val players = mutableListOf<Player>()

    override fun add(playerName: String): Boolean {
        if(players.any { it.name == playerName }) {
            return false
        }

        players.add(Player(playerName, 0))
        return true
    }

    override fun all(): List<String> {
        return players.map { it.name }
    }

    override fun updatePositionOf(player: String, position: Int) {
        TODO("Not yet implemented")
    }

    override fun positionOf(player: String): Int {
        return players.single { p -> p.name == player }.position
    }

    data class Player(val name: String, val position: Int)
}
