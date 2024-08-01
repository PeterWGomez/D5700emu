package org.example

import java.io.File

class emulator(
    // General purpose registers
    var r0: UByte,
    var r1: UByte,
    var r2: UByte,
    var r3: UByte,
    var r4: UByte,
    var r5: UByte,
    var r6: UByte,
    var r7: UByte,
    // Special registers
    var P: UShort,
    var T: UByte,
    var A: UShort,
    var M: UByte,     // There is no bit in Kotlin, using this
    // Screen
    var byteArray: ByteArray = ByteArray(64)
) {
    init {
        byteArray.fill(0)
    }

    fun runEmulator() {
        // Read the ROM
        val fileName = "roms/hello.d5700"
        val lines: List<String> = File(fileName).readLines()
        for (line in lines) {
            // Execute each line
            // Test to see command
            //println(line)
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
        println("Running Store")
        // Assign register the value
        if (rX == '0') {
            r0 = bb.toUByte(16)
            //println(r0)
            //test
            //println(r0.toByte().toChar())
        }
        if (rX == '1') {
            r1 = bb.toUByte(16)
            //println(r1)
        }
        if (rX == '2') {
            r2 = bb.toUByte(16)
            //println(r2)
        }
        if (rX == '3') {
            r3 = bb.toUByte(16)
            //println(r3)
        }
        if (rX == '4') {
            r4 = bb.toUByte(16)
            //println(r4)
        }
        if (rX == '5') {
            r5 = bb.toUByte(16)
            //println(r5)
        }
        if (rX == '6') {
            r6 = bb.toUByte(16)
            //println(r6)
        }
        if (rX == '7') {
            r7 = bb.toUByte(16)
            //println(r7)
        }
    }
    fun DRAW(rX: Char, rY: UByte, rZ: UByte) {
        // rX is the register being read. rY is the row, rZ is the column of where it will be printed
        //println("Running DRAW")
        // Assign register the value
        if (rX == '0') {
            byteArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = r0.toByte()
        }
        if (rX == '1') {
            byteArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = r1.toByte()
        }
        if (rX == '2') {
            byteArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = r2.toByte()
        }
        if (rX == '3') {
            byteArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = r3.toByte()
        }
        if (rX == '4') {
            byteArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = r4.toByte()
        }
        if (rX == '5') {
            byteArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = r5.toByte()
        }
        if (rX == '6') {
            byteArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = r6.toByte()
        }
        if (rX == '7') {
            byteArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = r7.toByte()
        }

        // Draw the current screen
        println("========")
        var charCounter = 0
        for (i in byteArray) {
            print(i.toChar())
            charCounter++
            if (charCounter % 8 == 0){
                println()
            }
        }
    }

}