package org.example

import java.io.File

@OptIn(ExperimentalUnsignedTypes::class)
class emulator(
    // General purpose registers
    var generalRegisters: UByteArray = UByteArray(8),
    // Special registers
    var P: UShort = 0u,
    var T: UByte = 0u,
    var A: UShort = 0u,
    var M: UByte = 0u,     // There is no bit in Kotlin, using this
    // Screen
    var screenArray: ByteArray = ByteArray(64)
) {
    init {
        screenArray.fill(0)
    }

    fun runEmulator(input: String) {
        // Read the ROM
        // Need error checking on input
        val fileName = "roms/${input}.d5700"
        val lines: List<String> = File(fileName).readLines()
        for (line in lines) {
            // Run STORE
            if (line[0] == '0'){
                STORE(line[1], line[2].toString()+line[3].toString())
            }
            // Run DRAW
            if (line[0] == 'F'){
                DRAW(line[1], line[2].toString().toUByte(16), line[3].toString().toUByte(16))
            }
            // Run READ_KEYBOARD
            if (line[0] == '6'){
                READ_KEYBOARD(line[1])
            }
            // Run ADD
            if (line[0] == '1'){
                ADD(line[1], line[2], line[3])
            }
            // Run SET_A
            if (line[0] == 'A'){
                SET_A(line[1].toString()+line[2].toString()+line[3].toString())
            }
            // Run READ
            if (line[0] == '3'){
                READ(line[1])
            }
            // Run COVERT_TO_BASE_10
            if (line[0] == 'D'){
                CONVERT_TO_BASE_10(line[1])
            }
            // Run CONVERT_BYTE_TO_ASCII
            if (line[0] == 'E'){
                CONVERT_BYTE_TO_ASCII(line[1], line[2])
            }
        }
    }

    fun STORE(rX: Char, bb: String) {
        // Assign register the value
        generalRegisters[rX.toString().toInt()] = bb.toUByte(16)
    }
    fun DRAW(rX: Char, rY: UByte, rZ: UByte) {
        // rX is the register being read. rY is the row, rZ is the column of where it will be printed
            screenArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = generalRegisters[rX.toString().toInt()].toByte()

        // Draw the current screen
        println("SCREEN DEBUG")
        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
        println("DEBUG: r${rX} as a char is ${generalRegisters[rX.toString().toInt()].toByte().toChar()}")
        println("DEBUG: row ${rY}")
        println("DEBUG: column ${rZ}")
        println("========")
        var charCounter = 0
        for (i in screenArray) {
            print(i.toChar())
            charCounter++
            if (charCounter % 8 == 0){
                println()
            }
        }
        println("========")
    }
    fun READ_KEYBOARD(rX: Char) {
        // Read in value from user
        println("Enter your input:")
        var input = readLine().toString()
        if (input == "") {
            input = "0"
        }
        // Assign register the value
        generalRegisters[rX.toString().toInt()] = input.toUByte(16)
        println("READ_KEYBOARD DEBUG")
        println("DEBUG: input is ${input}")
        println("DEBUG: r${rX} stored as ${generalRegisters[rX.toString().toInt()]}")
    }
    fun ADD(rX: Char, rY: Char, rZ: Char,) {
        // Assign register the value
//        var number1 = generalRegisters[rX.toString().toInt()].toInt()
//        var number2 = generalRegisters[rY.toString().toInt()].toInt()
//        var result = number1 + number2
        generalRegisters[rZ.toString().toInt()] =
            (generalRegisters[rX.toString().toInt()] + generalRegisters[rY.toString().toInt()]).toUByte()
        println("ADD DEBUG")
        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
        println("DEBUG: r${rY} is ${generalRegisters[rY.toString().toInt()]}")
        println("DEBUG: r${rZ} is ${generalRegisters[rZ.toString().toInt()]}")
//        println("number1 is ${number1}")
//        println("number2 is ${number2}")
//        println("result is ${result}")
    }
    fun SET_A(aaa: String) {
        // Assign register the value
        A = aaa.toUShort()
        println("SET_A DEBUG")
        println("aaa is ${aaa}")
        println("A = ${A}")
    }
    fun CONVERT_TO_BASE_10(rX: Char) {
        // Assign register the value
        println("CONVERT_TO_BASE_10 DEBUG")
        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
        println("DEBUG: r${rX} as string is ${(generalRegisters[rX.toString().toInt()].toByte().toString())}")
        SET_A(generalRegisters[rX.toString().toInt()].toByte().toString())
        //generalRegisters[rX.toString().toInt()] = bb.toUByte(16)
    }
    fun READ(rX: Char) {
        // Assign register the value
        println("READ DEBUG")
        println("DEBUG: A is ${A}")
        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
        generalRegisters[rX.toString().toInt()] = A.toString().toUByte(16)
        println("After transferring A, it is")
        println("DEBUG: A is ${A}")
        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
    }
    fun CONVERT_BYTE_TO_ASCII(rX: Char, rY: Char) {
        // Assign register the value
        println("CONVERT_BYTE_TO_ASCII DEBUG")
        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
        println("DEBUG: r${rY}  is ${(generalRegisters[rY.toString().toInt()])}")
        generalRegisters[rY.toString().toInt()] = generalRegisters[rX.toString().toInt()]
        println("DEBUG: After conversion, r${rY}  is ${(generalRegisters[rY.toString().toInt()])}")
    }

}