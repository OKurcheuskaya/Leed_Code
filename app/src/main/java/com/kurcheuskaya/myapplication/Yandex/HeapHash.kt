package com.kurcheuskaya.myapplication.Yandex

fun main() {
    println(topKFrequent(arrayOf("i", "love", "leetcode", "i", "love", "coding"), k = 2))
}

fun topKFrequent(words: Array<String>, k: Int): List<String> {
    val wordCount= words.groupingBy { it }.eachCount()
    val sortedWords = wordCount.toList().sortedWith(compareBy({ -it.second }, { it.first }))
    return sortedWords.take(k).map { it.first }
}