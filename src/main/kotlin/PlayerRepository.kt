interface PlayerRepository {
    fun add(player: String): Boolean
    fun all(): List<String>
}
