import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GooseGameAppTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)
    private val commandFactory: CommandFactory = mockk()
    private val command: Command = mockk()

    @Test
    fun `execute a command`() {
        val app = GooseGameApp(outputPrinter, commandFactory)
        every { commandFactory.build(any()) } returns command
        every { command.exec() } returns "a command has been executed."

        app.exec("aCommand")

        verify { outputPrinter.printLine("a command has been executed.") }
    }
}