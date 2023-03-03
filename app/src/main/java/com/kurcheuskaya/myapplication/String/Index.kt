package com.kurcheuskaya.myapplication.String

fun main() {
    println(strStr("leetcode", needle = "leeto"))
}

/**
 * Given two strings needle and haystack,
 * return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
fun strStr(haystack: String, needle: String): Int {
    return haystack.indexOf(needle)
}