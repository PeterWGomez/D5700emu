package com.example.emulatorops

interface emulatorOperations {
    fun SET_A(aaa: String): String
    fun SET_T(B: String, bb: String): String
    fun READ_T(C: String, rX: String): String
    fun CONVERT_TO_BASE_10(rX: String): String
    fun READ(rX: Char): String
    fun CONVERT_BYTE_TO_ASCII(rX: Char, rY: Char): String
    fun STORE(rX: String, bb: String)
    fun DRAW(rX: String, rY: String, rZ: String, command: String)
    fun READ_KEYBOARD(rX: String)
}