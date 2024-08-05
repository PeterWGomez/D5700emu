package org.example

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
) {

    fun runEmulator(input: String) {
        // Read the ROM
        // Need error checking on input
        var myMath = MathEmu()
        val fileName = "roms/${input}.d5700"
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
                var result = myMath.ADD(generalRegisters[line[1].toString().toInt()].toInt(), generalRegisters[line[2].toString().toInt()].toInt())
                STORE(line[3].toString(), result)
            }
            // Run SUB
            if (line[0] == '2'){
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

    fun STORE(rX: String, bb: String) {
        // Assign register the value
        generalRegisters[rX.toInt()] = bb
        //println(bb)
        //println(generalRegisters[rX.toInt()].toInt(16).toChar())
    }
    fun DRAW(rX: String, rY: String, rZ: String, command: String) {
        // rX is the register being read. rY is the row, rZ is the column of where it will be printed
        if (command == "hello") {
            screenArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = generalRegisters[rX.toInt()].toInt(16).toChar().toString()
        }
        if (command == "addition" || command == "subtraction") {
            screenArray[(rY.toInt()+1)*(rZ.toInt()+1)-1] = generalRegisters[rX.toInt()].toInt(16).toString()
        }
        // Draw the current screen
        println(generalRegisters[rX.toInt()])
        println(screenArray[(rY.toInt()+1)*(rZ.toInt()+1)-1])
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
    fun READ_KEYBOARD(rX: String) {
        // Read in value from user
        println("Enter your input:")
        var input = readLine().toString()
        if (input == "") {
            input = "0"
        }
        // Assign register the value
        STORE(rX, input)
    }
//    fun SET_A(aaa: String) {
//        // Assign register the value
//        A = aaa
//        println("SET_A DEBUG")
//        println("aaa is ${aaa}")
//        println("A = ${A}")
//    }
//    fun CONVERT_TO_BASE_10(rX: String) {
//        // Assign register the value
//        SET_A(generalRegisters[rX.toInt()])
//        //generalRegisters[rX.toString().toInt()] = bb.toUByte(16)
//    }
//    fun READ(rX: Char) {
//        // Assign register the value
//        println("READ DEBUG")
//        println("DEBUG: A is ${A}")
//        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
//        generalRegisters[rX.toString().toInt()] = A.toString().toUByte(16)
//        println("After transferring A, it is")
//        println("DEBUG: A is ${A}")
//        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
//    }
//    fun CONVERT_BYTE_TO_ASCII(rX: Char, rY: Char) {
//        // Assign register the value
//        println("CONVERT_BYTE_TO_ASCII DEBUG")
//        println("DEBUG: r${rX} is ${generalRegisters[rX.toString().toInt()]}")
//        println("DEBUG: r${rY}  is ${(generalRegisters[rY.toString().toInt()])}")
//        generalRegisters[rY.toString().toInt()] = generalRegisters[rX.toString().toInt()]
//        println("DEBUG: After conversion, r${rY}  is ${(generalRegisters[rY.toString().toInt()])}")
//    }

}