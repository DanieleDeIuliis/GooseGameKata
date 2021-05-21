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

    override fun updatePositionOf(playerName: String, position: Int) {
        playerBy(playerName).position = position
    }

    override fun positionOf(playerName: String): Int {
        return playerBy(playerName).position
    }

    private fun playerBy(playerName: String) = players.single { p -> p.name == playerName }

    data class Player(val name: String, var position: Int)
}
