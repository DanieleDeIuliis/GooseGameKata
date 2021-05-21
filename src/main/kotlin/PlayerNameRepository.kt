interface PlayerNameRepository {
    fun add(playerName: String): Boolean
    fun all(): List<String>
}

interface PlayerPositionRepository {
    fun updatePositionOf(playerName: String, position: Int)
    fun positionOf(playerName: String): Int
}

interface PlayerRepository: PlayerNameRepository, PlayerPositionRepository