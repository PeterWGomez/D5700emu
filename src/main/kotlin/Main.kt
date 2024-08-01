package org.example

fun main() {
    var input = ""
    do {
        println("Enter rom name (enter exit to close):")
        input = readLine().toString()
        val emulatorInstance = emulator(0u, 0u, 0u, 0u, 0u, 0u, 0u, 0u, 0u, 0u, 0u, 0u, )
        emulatorInstance.runEmulator()
    } while (input != "exit")
}