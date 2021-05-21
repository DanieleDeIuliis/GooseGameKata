interface PlayerNameRepository {
    fun add(player: String): Boolean
    fun all(): List<String>
}

interface PlayerPositionRepository {
    fun updatePositionOf(player: String, position: Int)
    fun positionOf(player: String): Int
}

interface PlayerRepository: PlayerNameRepository, PlayerPositionRepository