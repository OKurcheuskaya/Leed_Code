package com.kurcheuskaya.myapplication

fun main() {
    println(isPalindrome(123))
    println(isPalindrome(-121))
    println(partition("aab"))
}

fun isPalindrome(x: Int): Boolean {
    val list = x.toString().toCharArray()
    val firstList = arrayOfNulls<Char>(list.size)
    val secondList = arrayOfNulls<Char>(list.size)
    val count = list.size - 1
    for (index in list.indices) {
        firstList[count - index] = list[index]
        secondList[index] = list[index]
    }
    return firstList.contentEquals(secondList)
}

fun partition(s: String): List<List<String>> {
    val result = mutableListOf<List<String>>()
    val partition = mutableListOf<String>()
    dfs(s, 0, partition, result)
    return result
}

fun dfs(
    s: String,
    start: Int,
    partition: MutableList<String>,
    result: MutableList<List<String>>
) {
    if (start == s.length) {
        result.add(partition.toList())
        return
    }
    for (i in start until s.length) {
        if (isPalindrome(s, start, i)) {
            partition.add(s.substring(start, i + 1))
            dfs(s, i + 1, partition, result)
            partition.removeAt(partition.size - 1)
        }
    }
}

fun isPalindrome(s: String, start: Int, end: Int): Boolean {
    var right = end
    var left = start
    while (left < right) {
        if (s[left] != s[right]) return false
        left++
        right--
    }
    return true
}