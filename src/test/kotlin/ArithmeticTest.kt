import org.example.Emulator
import org.example.MathEmu
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArithmeticTest {
    @Test
    fun additionTest() {
        // Tests adding 2 numbers
        var myMath = MathEmu()
        assertEquals(myMath.ADD(6,8), "e")
        assertEquals(myMath.ADD(22,58), "50")
        assertEquals(myMath.ADD(100,687), "313")
        assertEquals(myMath.ADD(99999,99999), "30d3e")
        // Invalid numbers are screened before entering the ADD function using toIntOrNull()
        var badInput1 = "This is not a number"
        var badInput2 = "999999999999999999"
        var badInput3 = "-99999999999999999"
        assertEquals(badInput1.toIntOrNull(), null)
        assertEquals(badInput2.toIntOrNull(), null)
        assertEquals(badInput3.toIntOrNull(), null)
    }
    @Test
    fun subtractionTest() {
        // Tests adding 2 numbers
        var myMath = MathEmu()
        assertEquals(myMath.SUB(6,8), "-2")
        assertEquals(myMath.SUB(22,58), "-24")
        assertEquals(myMath.SUB(100,687), "-24b")
        assertEquals(myMath.SUB(99999,99999), "0")
        // Invalid numbers are screened before entering the SUB function using toIntOrNull()
        var badInput1 = "This is not a number"
        var badInput2 = "999999999999999999"
        var badInput3 = "-99999999999999999"
        assertEquals(badInput1.toIntOrNull(), null)
        assertEquals(badInput2.toIntOrNull(), null)
        assertEquals(badInput3.toIntOrNull(), null)
    }
}