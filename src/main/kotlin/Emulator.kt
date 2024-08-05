package org.example

import com.example.emulatorops.emulatorOperations
import java.io.File

class Emulator(
    // General purpose registers
    var generalRegisters: Array<String> = arrayOf("0", "0", "0", "0", "0", "0", "0", "0"),
    // Special registers
    var P: String = "0",
    var T: String = "0",
    var A: String = "0",
    var M: String = "0",
    // Screen
    val screenArray: Array<String> = Array(64) { "0" }
): emulatorOperations {

    fun runEmulator(input: String) {
        // Read the ROM
        var fileName = ""
        var myMath = MathEmu()
        if (input == "hello" || input == "addition" || input == "subtraction") {
            fileName = "roms/${input}.d5700"
        }
        else {
            println("Program ${input} is not installed on this system.")
            return
        }
        val lines: List<String> = File(fileName).readLines()
        for (line in lines) {
            // Run STORE
            if (line[0] == '0'){
                STORE(line[1].toString(), line[2].toString()+line[3].toString())
            }
            // Run DRAW
            if (line[0] == 'F'){
                DRAW(line[1].toString(), line[2].toString(), line[3].toString(), input)
            }
            // Run READ_KEYBOARD
            if (line[0] == '6'){
                READ_KEYBOARD(line[1].toString())
            }
            // Run ADD
            if (line[0] == '1'){
                if (generalRegisters[line[1].toString().toInt()].toIntOrNull() == null || generalRegisters[line[2].toString().toInt()].toIntOrNull() == null) {
                    println("One or more inputs were not integers, exiting...")
                    return
                }
                var result = myMath.ADD(generalRegisters[line[1].toString().toInt()].toInt(), generalRegisters[line[2].toString().toInt()].toInt())
                STORE(line[3].toString(), result)
            }
            // Run SUB
            if (line[0] == '2'){
                if (generalRegisters[line[1].toString().toInt()].toIntOrNull() == null || generalRegisters[line[2].toString().toInt()].toIntOrNull() == null) {
                    println("One or more inputs were not integers, exiting...")
                    return
                }
                var result = myMath.SUB(generalRegisters[line[1].toString().toInt()].toInt(), generalRegisters[line[2].toString().toInt()].toInt())
                STORE(line[3].toString(), result)
            }
//            // Run SET_A
//            if (line[0] == 'A'){
//                SET_A(line[1].toString()+line[2].toString()+line[3].toString())
//            }
//            // Run READ
//            if (line[0] == '3'){
//                READ(line[1])
//            }
//            // Run COVERT_TO_BASE_10
//            if (line[0] == 'D'){
//                CONVERT_TO_BASE_10(line[1].toString())
//            }
//            // Run CONVERT_BYTE_TO_ASCII
//            if (line[0] == 'E'){
//                CONVERT_BYTE_TO_ASCII(line[1], line[2])
//            }
        }
    }

    override fun SET_A(aaa: String): String {
        TODO("Not yet implemented")

    }

    override fun SET_T(B: String, bb: String): String {
        TODO("Not yet implemented")
    }

    override fun READ_T(C: String, rX: String): String {
        TODO("Not yet implemented")
    }

    override fun CONVERT_TO_BASE_10(rX: String): String {
        TODO("Not yet implemented")
    }

    override fun READ(rX: Char): String {
        TODO("Not yet implemented")
    }

    override fun CONVERT_BYTE_TO_ASCII(rX: Char, rY: Char): String {
        TODO("Not yet implemented")
    }

    override fun STORE(rX: String, bb: String) {
        // Assign register the value
        generalRegisters[rX.toInt()] = bb
    }
    override fun DRAW(rX: String, rY: String, rZ: String, command: String) {
        // rX is the register being read. rY is the row, rZ is the column of where it will be printed
        if (command == "hello") {
            screenArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = generalRegisters[rX.toInt()].toInt(16).toChar().toString()
        }
        if (command == "addition" || command == "subtraction") {
            screenArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = generalRegisters[rX.toInt()].toInt(16).toString()
        }
        // Draw the current screen
        println("========")
        var charCounter = 0
        var lineCounter = 0
        for (i in screenArray) {
            if (i.length == 1) {
                print(i)
                charCounter++
                if (charCounter % 8 == 0){
                    lineCounter++
                    println()
                }
            }
            else if (i.length > 1) {
                for (letter in i){
                    print(letter)
                    charCounter++
                    if (charCounter % 8 == 0){
                        lineCounter++
                        println()
                    }
                }
            }
            if (lineCounter == 8){
                return
            }
        }
        println("========")
    }
    override fun READ_KEYBOARD(rX: String) {
        // Read in value from user
        println("Enter your input:")
        var input = readLine().toString()
        if (input == "") {
            input = "0"
        }
        // Assign register the value
        STORE(rX, input)
    }
}