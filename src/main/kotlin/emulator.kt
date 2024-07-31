package org.example

import java.io.File

class emulator {

    fun runEmulator() {
        val fileName = "roms/hello.d5700"
        val lines: List<String> = File(fileName).readLines()
        for (line in lines) {
            println(line)
        }
    }
}