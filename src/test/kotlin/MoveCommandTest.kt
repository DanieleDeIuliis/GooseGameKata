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

        assertThat(result).isEqualTo("Paperino rolls 4, 2. Paperino moves from Start to 6")
    }

    @Test
    fun `move a player twice from start position`() {
        val repository: PlayerPositionRepository = mockk(relaxed = true)
        val app = MoveCommand(repository, MoveCommandData("Paperino", 4, 2))
        every { repository.positionOf("Paperino") } returns 8

        val result = app.exec()

        verify { repository.updatePositionOf("Paperino", 14) }
        assertThat(result).isEqualTo("Paperino rolls 4, 2. Paperino moves from 8 to 14")
    }

    @Test
    fun `player wins`() {
        val repository: PlayerPositionRepository = mockk(relaxed = true)
        val app = MoveCommand(repository, MoveCommandData("Paperino", 2, 2))
        every { repository.positionOf("Paperino") } returns 59

        val result = app.exec()

        verify { repository.updatePositionOf("Paperino", 63) }
        assertThat(result).isEqualTo("Paperino rolls 2, 2. Paperino moves from 59 to 63. Paperino Wins!!")
    }

    @Test
    fun `player bounces back`() {
        val repository: PlayerPositionRepository = mockk(relaxed = true)
        val app = MoveCommand(repository, MoveCommandData("Paperino", 5, 3))
        every { repository.positionOf("Paperino") } returns 58

        val result = app.exec()

        verify { repository.updatePositionOf("Paperino", 60) }
        assertThat(result).isEqualTo("Paperino rolls 5, 3. Paperino moves from 58 to 63. Paperino bounces! Paperino returns to 60")
    }
}