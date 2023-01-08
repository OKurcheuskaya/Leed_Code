package com.kurcheuskaya.myapplication

fun main() {
    println(canCompleteCircuit(intArrayOf(1, 2, 3, 4, 5), intArrayOf(3, 4, 5, 1, 2)))
    println(maxPoints(arrayOf(intArrayOf(2, 3), intArrayOf(3, 3), intArrayOf(-5, 3))))
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

fun maxPoints(points: Array<IntArray>): Int {
    if (points.size < 3) return points.size

    var result = 0
    for (i in points.indices) {
        // map to store slope and its frequency
        val map = HashMap<Double, Int>()
        var samePoint = 0
        var localMax = 0
        for (j in i + 1 until points.size) {
            val x1 = points[i][0]
            val y1 = points[i][1]
            val x2 = points[j][0]
            val y2 = points[j][1]

            if (x1 == x2 && y1 == y2) {
                samePoint++
                continue
            }
            var slope = if (x1 == x2) {
                if (y1 == y2) Double.MIN_VALUE else Double.MAX_VALUE
            } else {
                (y1 - y2).toDouble() / (x1 - x2).toDouble()
            }
            if(slope ==-0.0) slope = 0.0
            if (map.containsKey(slope)) {
                map[slope] = map[slope]!! + 1
            } else {
                map[slope] = 1
            }
            localMax = Math.max(localMax, map[slope]!!)
        }

        result = Math.max(result, localMax + samePoint + 1)
    }
    return result
}