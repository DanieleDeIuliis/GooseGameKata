import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddPlayerAcceptanceTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)
    private lateinit var app: GooseGameApp

    @BeforeEach
    fun setUp() {
        app = GooseGameApp(outputPrinter, InMemoryPlayer())
    }

    @Test
    fun `add players`() {
        app.exec("add player Pippo")
        app.exec("add player Pluto")
        app.exec("add player Paperino")

        verify { outputPrinter.printLine("Players: Pippo") }
        verify { outputPrinter.printLine("Players: Pippo, Pluto") }
        verify { outputPrinter.printLine("Players: Pippo, Pluto, Paperino") }
    }

    @Test
    fun `player already exists`() {
        app.exec("add player Pluto")
        app.exec("add player Pluto")

        verify { outputPrinter.printLine("Players: Pluto") }
        verify { outputPrinter.printLine("Pluto: already existing player") }
    }
}

