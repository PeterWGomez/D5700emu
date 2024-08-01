package org.example

import java.io.File

class emulator(
    // General purpose registers
    var r0: Byte,
    var r1: Byte,
    var r2: Byte,
    var r3: Byte,
    var r4: Byte,
    var r5: Byte,
    var r6: Byte,
    var r7: Byte,
    // Special registers
    var P: Short,
    var T: Byte,
    var A: Short,
    var M: Byte     // There is no bit in Kotlin, using this
) {

    fun runEmulator() {
        val fileName = "roms/hello.d5700"
        val lines: List<String> = File(fileName).readLines()
        for (line in lines) {
            println(line)
        }
    }

    fun STORE() {

    }
}