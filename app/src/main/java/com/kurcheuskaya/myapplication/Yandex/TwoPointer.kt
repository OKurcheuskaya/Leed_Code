package com.kurcheuskaya.myapplication.Yandex

fun main() {
    println(maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))
    println(maxArea(intArrayOf(2, 3, 10, 5, 7, 8, 9)))
}

fun maxArea(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var amount = 0
    while (left < right) {
        val current = minOf(height[left], height[right]) * (right - left)
        amount = maxOf(amount, current)
        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
    }
    return amount
}