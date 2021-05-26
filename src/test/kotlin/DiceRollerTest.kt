import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DiceRollerTest {
    @Test
    fun `rolls two values between 1 and 6 included`() {
        val diceRoller = DiceRoller()

        val rolls = diceRoller.roll()

        assertThat(rolls.first).isGreaterThanOrEqualTo(1)
        assertThat(rolls.first).isLessThanOrEqualTo(6)
        assertThat(rolls.second).isGreaterThanOrEqualTo(1)
        assertThat(rolls.second).isLessThanOrEqualTo(6)
    }
}