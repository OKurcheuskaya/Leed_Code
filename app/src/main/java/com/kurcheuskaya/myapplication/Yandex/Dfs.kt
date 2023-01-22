package com.kurcheuskaya.myapplication.Yandex

fun main() {
    println(
        numIslands(
            arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '0', '1', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '0', '0', '0')
            )
        )
    )
    println(removeInvalidParentheses(s = "()())()"))
    println(partition("aad"))
}

fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty() || grid[0].isEmpty()) return 0
    var count = 0
    val rows = grid.size
    val cols = grid[0].size
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            if (grid[i][j] == '1') {
                count++
                dfs(grid, i, j)
            }
        }
    }
    return count
}

private fun dfs(grid: Array<CharArray>, i: Int, j: Int) {
    if (i < 0 || i >= grid.size || j < 0 || j >= grid[0].size || grid[i][j] == '0') return
    grid[i][j] = '0'
    dfs(grid, i + 1, j)
    dfs(grid, i - 1, j)
    dfs(grid, i, j + 1)
    dfs(grid, i, j - 1)
}

var minRemovals = 0
fun removeInvalidParentheses(s: String): List<String> {
    val result = mutableSetOf<String>()
    minRemovals = s.length
    dfs(s, 0, 0, 0, "", result, minRemovals)
    return result.toList()
}

private fun dfs(
    s: String,
    index: Int,
    leftCount: Int,
    rightCount: Int,
    current: String,
    result: MutableSet<String>,
    minRemoval: Int
) {
    minRemovals = minRemoval
    if (index == s.length) {
        if (leftCount == rightCount) {
            if (minRemovals > s.length - current.length) {
                result.clear()
                minRemovals = s.length - current.length
            }
            if (minRemovals == s.length - current.length) {
                result.add(current)
            }
        }
        return
    }
    if (s[index] != '(' && s[index] != ')') {
        dfs(s, index + 1, leftCount, rightCount, current + s[index], result, minRemovals)
    } else {
        if (s[index] == '(') {
            dfs(s, index + 1, leftCount + 1, rightCount, current + s[index], result, minRemovals)
            dfs(s, index + 1, leftCount, rightCount, current, result, minRemovals)
        } else {
            if (leftCount > rightCount) {
                dfs(
                    s,
                    index + 1,
                    leftCount,
                    rightCount + 1,
                    current + s[index],
                    result,
                    minRemovals
                )
            }
            dfs(s, index + 1, leftCount, rightCount, current, result, minRemovals)
        }
    }
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

