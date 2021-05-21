import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GooseGameAppTest {

    private val outputPrinter: OutputPrinter = mockk(relaxed = true)

    @Test
    fun `add player`() {
        val repository = mockk<PlayerRepository>(relaxed = true)
        val app = GooseGameApp(outputPrinter, repository)
        every { repository.all() } returns listOf("Pluto")
        every { repository.add("Pluto") } returns true

        app.exec("add player Pluto")

        verify { outputPrinter.printLine("Players: Pluto") }
    }

    @Test
    fun `add two player`() {
        val repository = mockk<PlayerRepository>(relaxed = true)
        val app = GooseGameApp(outputPrinter, repository)
        every { repository.all() } returns listOf("Pluto, Pippo")
        every { repository.add(any()) } returns true

        app.exec("add player Pluto")
        app.exec("add player Pippo")

        verify { outputPrinter.printLine("Players: Pluto, Pippo") }
    }

    @Test
    fun `add player already exist`() {
        val playerRepository = mockk<PlayerRepository>()
        val app = GooseGameApp(outputPrinter, playerRepository)

        every { playerRepository.add("Pluto") } returns false

        app.exec("add player Pluto")

        verify { outputPrinter.printLine("Pluto: already existing player") }
    }

    @Test
    fun `move a player from start position`() {
        val playerRepository = mockk<PlayerRepository>()
        val app = GooseGameApp(outputPrinter, playerRepository)

        app.exec("move Paperino 4, 2")

        verify { outputPrinter.printLine("Paperino rolls, 4, 2. Paperino moves from Start to 6") }
    }
}