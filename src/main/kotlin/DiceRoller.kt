import kotlin.random.Random
import kotlin.random.nextInt

class DiceRoller {
    fun roll(): Rolls {
        return Rolls(Random.nextInt(1..6), Random.nextInt(1..6))
    }
}
