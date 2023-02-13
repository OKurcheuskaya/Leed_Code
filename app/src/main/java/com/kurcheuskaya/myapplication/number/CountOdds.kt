package com.kurcheuskaya.myapplication.number


fun main() {
    println(countOdds(3, 7))
    println(countOdds(8, 10))
}

fun countOdds(low: Int, high: Int): Int {
    return if (low % 2 == 0 && high % 2 == 0) {
        ((high - low) / 2)
    } else {
        ((high - low) / 2) + 1
    }
}