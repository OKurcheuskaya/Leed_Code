package com.kurcheuskaya.myapplication.Yandex

fun main() {
    println(topKFrequent(arrayOf("i", "love", "leetcode", "i", "love", "coding"), k = 2))
    println(topKFrequent(nums = intArrayOf(4, 1, -1, 2, -1, 2, 3), k = 2))
}

fun topKFrequent(words: Array<String>, k: Int): List<String> {
    val wordCount = words.groupingBy { it }.eachCount()
    val sortedWords = wordCount.toList().sortedWith(compareBy({ -it.second }, { it.first }))
    return sortedWords.take(k).map { it.first }
}

fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val new = nums.groupBy { it }.toList()
    return new.sortedByDescending { it.second.size }.take(k).map { it.first }.toIntArray()
}