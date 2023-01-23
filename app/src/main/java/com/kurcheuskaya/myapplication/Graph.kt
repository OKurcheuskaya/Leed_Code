package com.kurcheuskaya.myapplication

import java.util.*


fun main() {
    println(longestPath(intArrayOf(-1, 0, 0, 1, 1, 2), s = "abacbe"))
//    println(findJudge(n = 2, trust = arrayOf(intArrayOf(1, 2))))
    println(findJudge(n = 3, trust = arrayOf(intArrayOf(1,3), intArrayOf(2,3))))
}

var longValue = 1 // variable to store the length of the longest path


fun longestPath(parent: IntArray, s: String): Int {
    // create an adjacency list representation of the tree
    val adj: MutableMap<Int, MutableList<Int>> = HashMap()
    for (i in 1 until parent.size) {
        val j = parent[i]
        adj.putIfAbsent(j, LinkedList())
        adj[j]!!.add(i)
    }
    // call dfs on the root of the tree
    dfs(0, adj, s)
    return longValue
}

fun dfs(node: Int, adj: Map<Int, MutableList<Int>>, s: String): Int {
    if (!adj.containsKey(node)) return 1
    var max = 0
    var secondMax = 0
    for (nbrNode in adj[node]!!) {
        val longNode = dfs(nbrNode, adj, s)
        // if the characters at the current node and its neighbor are the same, ignore the neighbor
        if (s[node] == s[nbrNode]) continue
        // update max and secondMax with the longest path from the neighbor node
        if (longNode > max) {
            secondMax = max
            max = longNode
        } else if (longNode > secondMax) {
            secondMax = longNode
        }
    }
    // update longestPathValue with the longest path that includes the current node
    longValue = Math.max(longValue, max + secondMax + 1)
    return max + 1
}

fun findJudge(n: Int, trust: Array<IntArray>): Int {
    val first = IntArray(n + 1)
    val second = IntArray(n + 1)
    for (item in trust) {
        first[item[1]]++
        second[item[0]]++
    }
    for (person in 1..n) {
        if (first[person] == n - 1 && second[person] == 0) return person

    }
    return -1
}