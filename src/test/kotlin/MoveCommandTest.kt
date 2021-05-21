import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoveCommandTest {

    @Test
    fun `move a player from start position`() {
        val repository: PlayerPositionRepository = mockk(relaxed = true)
        val app = MoveCommand(repository, MoveCommandData("Paperino", 4,2))
        every { repository.positionOf("Paperino") } returns 0

        val result = app.exec()

        assertThat(result).isEqualTo("Paperino rolls, 4, 2. Paperino moves from Start to 6")
    }

    @Test
    fun `move a player twice from start position`() {
        val repository: PlayerPositionRepository = mockk(relaxed = true)
        val app = MoveCommand(repository, MoveCommandData("Paperino", 4, 2))
        every { repository.positionOf("Paperino") } returns 8

        val result = app.exec()

        verify { repository.updatePositionOf("Paperino", 14) }
        assertThat(result).isEqualTo("Paperino rolls, 4, 2. Paperino moves from 8 to 14")
    }
}