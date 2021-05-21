import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InMemoryPlayerTest {
    @Test
    fun `add a player`() {
        val isAdded = InMemoryPlayer().add("aPlayerName")

        assertThat(isAdded).isTrue
    }

    @Test
    fun `return false when player already exists`() {
        val repository = InMemoryPlayer()
        repository.add("aPlayerName")
        val isAdded = repository.add("aPlayerName")

        assertThat(isAdded).isFalse
    }

    @Test
    fun `retrieve all players`() {
        val repository = InMemoryPlayer()
        repository.add("aPlayerName")
        repository.add("aPlayerName2")

        assertThat(repository.all()).isEqualTo(listOf("aPlayerName", "aPlayerName2"))
    }

    @Test
    fun `get a new player's current position`() {
        val repository = InMemoryPlayer()

        repository.add("Player")
        val result = repository.positionOf("Player")

        assertThat(result).isEqualTo(0)
    }
}