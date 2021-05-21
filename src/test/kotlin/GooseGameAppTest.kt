import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GooseGameAppTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)

    @Test
    fun `add player`() {
        val app = GooseGameApp(outputPrinter)

        app.exec("add player Pluto")

        verify { outputPrinter.printLine("Players: Pluto") }
    }

    @Test
    fun `add two player`() {
        val app = GooseGameApp(outputPrinter)

        app.exec("add player Pluto")
        app.exec("add player Pippo")

        verify { outputPrinter.printLine("Players: Pluto, Pippo") }
    }
}