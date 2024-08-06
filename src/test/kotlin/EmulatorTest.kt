import org.example.Emulator
import org.example.MathEmu
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EmulatorTest {
    @Test
    fun constructorTest() {
        // Check initialized emulator
        val emulatorInstance = Emulator()
        for (i in emulatorInstance.generalRegisters) {
            assertEquals(i, "0")
        }
        assertEquals(emulatorInstance.P, "0")
        assertEquals(emulatorInstance.T, "0")
        assertEquals(emulatorInstance.A, "0")
        assertEquals(emulatorInstance.M, "0")
        for (i in emulatorInstance.screenArray) {
            assertEquals(i, "0")
        }
    }
    @Test
    fun storeTest() {
        // Check Store function
        val emulatorInstance = Emulator()
        emulatorInstance.STORE("0", "48")
        emulatorInstance.STORE("1", "45")
        emulatorInstance.STORE("2", "4C")
        emulatorInstance.STORE("3", "4C")
        emulatorInstance.STORE("4", "4F")
        emulatorInstance.STORE("5", "48")
        emulatorInstance.STORE("6", "45")
        emulatorInstance.STORE("7", "59")
        assertEquals(emulatorInstance.generalRegisters[0], "48")
        assertEquals(emulatorInstance.generalRegisters[1], "45")
        assertEquals(emulatorInstance.generalRegisters[2], "4C")
        assertEquals(emulatorInstance.generalRegisters[3], "4C")
        assertEquals(emulatorInstance.generalRegisters[4], "4F")
        assertEquals(emulatorInstance.generalRegisters[5], "48")
        assertEquals(emulatorInstance.generalRegisters[6], "45")
        assertEquals(emulatorInstance.generalRegisters[7], "59")
    }
    @Test
    fun drawTest() {
        // Check Draw function
        val emulatorInstance = Emulator()
        emulatorInstance.STORE("0", "48")
        emulatorInstance.STORE("1", "45")
        emulatorInstance.STORE("2", "4C")
        emulatorInstance.STORE("3", "4C")
        emulatorInstance.STORE("4", "4F")
        emulatorInstance.STORE("5", "48")
        emulatorInstance.STORE("6", "45")
        emulatorInstance.STORE("7", "59")
        emulatorInstance.DRAW("0", "0", "0", "hello")
        emulatorInstance.DRAW("1", "0", "1", "hello")
        emulatorInstance.DRAW("2", "0", "2", "hello")
        emulatorInstance.DRAW("3", "0", "3", "hello")
        emulatorInstance.DRAW("4", "0", "4", "hello")
        emulatorInstance.DRAW("5", "0", "5", "hello")
        emulatorInstance.DRAW("6", "0", "6", "hello")
        emulatorInstance.DRAW("7", "0", "7", "hello")
        assertEquals(emulatorInstance.screenArray[0], "H")
        assertEquals(emulatorInstance.screenArray[1], "E")
        assertEquals(emulatorInstance.screenArray[2], "L")
        assertEquals(emulatorInstance.screenArray[3], "L")
        assertEquals(emulatorInstance.screenArray[4], "O")
        assertEquals(emulatorInstance.screenArray[5], "H")
        assertEquals(emulatorInstance.screenArray[6], "E")
        assertEquals(emulatorInstance.screenArray[7], "Y")
        // Check Draw for arithmetic
        emulatorInstance.STORE("0", "1")
        emulatorInstance.STORE("1", "2")
        emulatorInstance.STORE("2", "3")
        emulatorInstance.STORE("3", "4")
        emulatorInstance.STORE("4", "5")
        emulatorInstance.STORE("5", "6")
        emulatorInstance.STORE("6", "7")
        emulatorInstance.STORE("7", "8")
        emulatorInstance.DRAW("0", "0", "0", "addition")
        emulatorInstance.DRAW("1", "0", "1", "addition")
        emulatorInstance.DRAW("2", "0", "2", "addition")
        emulatorInstance.DRAW("3", "0", "3", "addition")
        emulatorInstance.DRAW("4", "0", "4", "subtraction")
        emulatorInstance.DRAW("5", "0", "5", "subtraction")
        emulatorInstance.DRAW("6", "0", "6", "subtraction")
        emulatorInstance.DRAW("7", "0", "7", "subtraction")
        assertEquals(emulatorInstance.screenArray[0], "1")
        assertEquals(emulatorInstance.screenArray[1], "2")
        assertEquals(emulatorInstance.screenArray[2], "3")
        assertEquals(emulatorInstance.screenArray[3], "4")
        assertEquals(emulatorInstance.screenArray[4], "5")
        assertEquals(emulatorInstance.screenArray[5], "6")
        assertEquals(emulatorInstance.screenArray[6], "7")
        assertEquals(emulatorInstance.screenArray[7], "8")
    }

}