import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class MovePlayerAcceptanceTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)
    private val app =  GooseGameApp(outputPrinter, CommandFactory(InMemoryPlayer()))

    @Test
    fun `move a player from start position`() {
        app.exec("add player Paperino")

        app.exec("move Paperino 5, 3")

        verify { outputPrinter.printLine("Paperino rolls, 5, 3. Paperino moves from Start to 8") }
    }
}