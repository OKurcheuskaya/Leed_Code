package com.kurcheuskaya.myapplication

fun main() {
    println(jump(intArrayOf(2, 3, 1, 1, 4)))
}

fun jump(nums: IntArray): Int {
    var jumps = 0
    var curEnd = 0
    var curFast = 0
    for (i in 0 until nums.size - 1) {
        curFast = maxOf(curFast, i + nums[i])
        if (curEnd == i) {
            jumps++
            curEnd = curFast
        }
    }
    return jumps
}