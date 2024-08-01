package org.example

fun main() {
    var input = ""
    do {
        println("Enter rom name (enter exit to close):")
        input = readLine().toString()
        val emulatorInstance = emulator()
        emulatorInstance.runEmulator()
    } while (input != "exit")
}