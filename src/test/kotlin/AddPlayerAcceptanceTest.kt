import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class AddPlayerAcceptanceTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)

    @Test
    fun `add players`() {
        val app = GooseGameApp(outputStream = outputPrinter)
        app.exec("add player Pippo")
        app.exec("add player Pluto")
        app.exec("add player Paperino")

        verify { outputPrinter.printLine("Players: Pippo") }
        verify { outputPrinter.printLine("Players: Pippo, Pluto") }
        verify { outputPrinter.printLine("Players: Pippo, Pluto, Paperino") }
    }
}

