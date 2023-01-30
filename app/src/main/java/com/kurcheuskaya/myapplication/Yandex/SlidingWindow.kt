package com.kurcheuskaya.myapplication.Yandex

import java.util.*

fun main() {
    println(maxSlidingWindow(intArrayOf(1), k = 1))
}

fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
    val res = DoubleArray(nums.size - k + 1) { 0.0 }
    val window = nums.take(k).sorted().toMutableList()
    res[0] = window.medianInSortedList()
    for (i in 1..nums.size - k) {
        nums[i - 1].also { window.remove(it) }
        nums[i + k - 1].also { window.addToSortedList(it) }
        res[i] = window.medianInSortedList()
    }
    return res
}

fun List<Int>.medianInSortedList(): Double {
    return if (size % 2 == 0) {
        (this[size / 2].toDouble() + this[size / 2 - 1]) / 2
    } else this[size / 2].toDouble()
}

fun MutableList<Int>.addToSortedList(e: Int) {
    if (isEmpty() || this.last() < e) {
        add(e); return
    }
    if (this.first() >= e) {
        add(0, e); return
    }
    for (i in indices.reversed()) {
        if (this[i] < e) {
            add(i + 1, e)
            return
        }
    }
}

//[1,3,-1,-3,5,3,6,7], k = 3
fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    var res = arrayListOf<Int>()
    for (index in 0 until nums.size - k + 1) {
        val newList = IntArray(nums.size - k + 1) { 0 }
        for (i in index until index + k) {
            newList[index] = nums[i]
        }
        res.add(newList.max())
    }
    return res.toIntArray()
}

fun maxSlidingWindowBetter(nums: IntArray, k: Int): IntArray {
    if (nums.isEmpty()) return intArrayOf()
    val res = IntArray(nums.size - k + 1)
    val deque = LinkedList<Int>()
    for (i in nums.indices) {
        while (deque.isNotEmpty() && nums[deque.peekLast()] <= nums[i]) {
            deque.pollLast()
        }
        deque.offerLast(i)
        if (deque.peekFirst() <= i - k) {
            deque.pollFirst()
        }
        if (i - k + 1 >= 0) {
            res[i - k + 1] = nums[deque.peekFirst()]
        }
    }
    return res
}
fun characterReplacement(s: String, k: Int): Int {
    val charCounts = IntArray(26) { 0 }
    var start = 0
    var maxCount = 0
    var maxLength = 0
    for (end in s.indices) {
        maxCount = maxOf(maxCount, ++charCounts[s[end] - 'A'])
        while (end - start + 1 - maxCount > k) {
            charCounts[s[start] - 'A']--
            start++
        }
        maxLength = maxOf(maxLength, end - start + 1)
    }
    return maxLength
}