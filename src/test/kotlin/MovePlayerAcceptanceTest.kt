import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class MovePlayerAcceptanceTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)
    private lateinit var app: GooseGameApp

    @BeforeEach
    fun setUp() {
        app = GooseGameApp(outputPrinter, CommandFactory(InMemoryPlayer()))
    }

    @Test
    fun `move a player from start position`() {
        app.exec("add player Paperino")

        app.exec("move Paperino 5, 3")

        verify { outputPrinter.printLine("Paperino rolls 5, 3. Paperino moves from Start to 8") }
    }

    @Test
    fun `Paperino wins!!`() {
        app.exec("add player Paperino")

        repeat(6) { app.exec("move Paperino 5, 5") }
        app.exec("move Paperino 1, 2")

        verify { outputPrinter.printLine("Paperino rolls 1, 2. Paperino moves from 60 to 63. Paperino Wins!!") }
    }

    @Test
    fun `player rolls a value greater than the maximum and bounces back`() {
        app.exec("add player Paperino")

        repeat(6) { app.exec("move Paperino 5, 5") }
        app.exec("move Paperino 4, 2")

        verify { outputPrinter.printLine("Paperino rolls 4, 2. Paperino moves from 60 to 63. Paperino bounces! Paperino returns to 60") }
    }
}