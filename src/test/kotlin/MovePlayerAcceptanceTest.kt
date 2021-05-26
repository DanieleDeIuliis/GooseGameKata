import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class MovePlayerAcceptanceTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)
    private val diceRoller: DiceRoller = mockk()
    private lateinit var app: GooseGameApp

    @BeforeEach
    fun setUp() {
        app = GooseGameApp(outputPrinter, CommandFactory(InMemoryPlayer(), diceRoller))
    }

    @Test
    fun `move a player from start position`() {
        every { diceRoller.roll() } returns Rolls(5, 3)
        app.exec("add player Paperino")

        app.exec("move Paperino")

        verify { outputPrinter.printLine("Paperino rolls 5, 3. Paperino moves from Start to 8") }
    }

    @Test
    fun `Paperino wins!!`() {
        every { diceRoller.roll() } returnsMany  listOf(Rolls(5, 5), Rolls(5, 5), Rolls(5, 5), Rolls(5, 5), Rolls(5, 5), Rolls(5, 5), Rolls(1, 2))
        app.exec("add player Paperino")

        repeat(6) { app.exec("move Paperino") }
        app.exec("move Paperino")

        verify { outputPrinter.printLine("Paperino rolls 1, 2. Paperino moves from 60 to 63. Paperino Wins!!") }
    }

    @Test
    fun `player rolls a value greater than the maximum and bounces back`() {
        every { diceRoller.roll() } returnsMany  listOf(Rolls(5, 5), Rolls(5, 5), Rolls(5, 5), Rolls(5, 5), Rolls(5, 5), Rolls(5, 5), Rolls(4, 2))
        app.exec("add player Paperino")

        repeat(6) { app.exec("move Paperino") }
        app.exec("move Paperino")

        verify { outputPrinter.printLine("Paperino rolls 4, 2. Paperino moves from 60 to 63. Paperino bounces! Paperino returns to 60") }
    }
}