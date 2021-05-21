import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class AddPlayerAcceptanceTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)

    @Test
    fun `add players`() {
        val repository = InMemoryPlayer()
        val app = GooseGameApp(outputStream = outputPrinter, mockk())
        app.exec("add player Pippo")
        app.exec("add player Pluto")
        app.exec("add player Paperino")

        verify { outputPrinter.printLine("Players: Pippo") }
        verify { outputPrinter.printLine("Players: Pippo, Pluto") }
        verify { outputPrinter.printLine("Players: Pippo, Pluto, Paperino") }
    }

    @Test
    fun `player already exists`() {
        val app = GooseGameApp(outputStream = outputPrinter, mockk())
        app.exec("add player Pluto")
        app.exec("add player Pluto")

        verify { outputPrinter.printLine("Players: Pluto") }
        verify { outputPrinter.printLine("Pluto: already existing player") }
    }
}

