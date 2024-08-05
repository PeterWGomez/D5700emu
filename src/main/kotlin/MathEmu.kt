package org.example

import com.example.arithmetic.ArithmeticOperations

class MathEmu: ArithmeticOperations {
    override fun ADD(num1: Int, num2: Int): String {
        // Assign register the value
        var result = num1 + num2
        var result2 = result.toString()
        var result3 = result2.toInt()
        var hexResult = result3.toString(16)
        return hexResult
        //STORE(rZ, hexResult)
    }
    override fun SUB(num1: Int, num2: Int): String {
        // Assign register the value
        var result = num1 - num2
        var result2 = result.toString()
        var result3 = result2.toInt()
        var hexResult = result3.toString(16)
        return hexResult
//        STORE(rZ, hexResult)
    }

    override fun MUL(a: Int, b: Int): String {
        TODO("Not yet implemented")
    }

    override fun DIV(a: Int, b: Int): String {
        TODO("Not yet implemented")
    }
}