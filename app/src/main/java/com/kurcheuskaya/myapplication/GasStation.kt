package com.kurcheuskaya.myapplication

fun main() {
    println(canCompleteCircuit(intArrayOf(1, 2, 3, 4, 5), intArrayOf(3, 4, 5, 1, 2)))
}

fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
    var start = 0
    var total = 0
    var tank = 0
    for (i in gas.indices) {
        tank += gas[i] - cost[i]
        if (tank < 0) {
            start = i + 1
            total += tank
            tank = 0
        }
    }
    return if (total + tank >= 0) start else -1
}