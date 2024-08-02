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

}