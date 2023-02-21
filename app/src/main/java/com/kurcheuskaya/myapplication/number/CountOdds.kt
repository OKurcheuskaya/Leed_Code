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
fun singleNonDuplicate(nums: IntArray): Int {
    var s = 0
    var e = nums.lastIndex
    while(s < e) {
        val s1 = nums[s]
        val s2 = nums[s + 1]
        val e1 = nums[e]
        val e2 = nums[e - 1]
        if (s1 != s2) return s1
        if (e1 != e2) return e1
        s += 2
        e -= 2
    }
    return nums[s]
}