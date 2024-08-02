package org.example

fun main() {
    var input = ""
    println("Enter rom name (enter exit to close):")
    input = readLine().toString()
    while (input != "exit") {
        val emulatorInstance = emulator()
        emulatorInstance.runEmulator(input)
        println("Enter rom name (enter exit to close):")
        input = readLine().toString()
    }
}