import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoveCommandTest {

    @Test
    fun `move a player from start position`() {
        val app = MoveCommand("move Paperino 4, 2")

        val result = app.exec()

        assertThat(result).isEqualTo("Paperino rolls, 4, 2. Paperino moves from Start to 6")
    }
}