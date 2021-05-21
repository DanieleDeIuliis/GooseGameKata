import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AddCommandTest {

    @Test
    fun `add player`() {
        val repository = mockk<PlayerRepository>(relaxed = true)
        val app = AddCommand("add player Pluto", repository)
        every { repository.all() } returns listOf("Pluto")
        every { repository.add("Pluto") } returns true

        val result = app.exec()

        assertThat(result).isEqualTo("Players: Pluto")
    }

    @Test
    fun `add two player`() {
        val repository = mockk<PlayerRepository>(relaxed = true)
        val app = AddCommand("add player Pippo", repository)
        every { repository.all() } returns listOf("Pluto, Pippo")
        every { repository.add(any()) } returns true

        val result = app.exec()

        assertThat(result).isEqualTo("Players: Pluto, Pippo")
    }

    @Test
    fun `add player already exist`() {
        val repository = mockk<PlayerRepository>(relaxed = true)
        val app = AddCommand("add player Pluto", repository)

        every { repository.add("Pluto") } returns false

        val result = app.exec()

        assertThat(result).isEqualTo("Pluto: already existing player")
    }
}