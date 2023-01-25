package com.kurcheuskaya.myapplication

import java.util.*
import kotlin.collections.HashMap


fun main() {
    println(longestPath(intArrayOf(-1, 0, 0, 1, 1, 2), s = "abacbe"))
//    println(findJudge(n = 2, trust = arrayOf(intArrayOf(1, 2))))
    println(findJudge(n = 3, trust = arrayOf(intArrayOf(1, 3), intArrayOf(2, 3))))
    println(closestMeetingNode(edges = intArrayOf(2, 2, 3, -1), node1 = 0, node2 = 1))
    println(closestMeetingNode(edges = intArrayOf(1, 2, -1), node1 = 0, node2 = 1))
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

fun closestMeetingNode(edges: IntArray, node1: Int, node2: Int): Int {
    val distances = mutableMapOf<Int, Int>()
    var n = node1
    var dist = 0
    while (n != -1) {
        if (distances.contains(n)) break
        distances[n] = dist
        n = edges[n]
        dist++
    }
    n = node2
    dist = 0
    var min = Int.MAX_VALUE
    var res = -1
    while (n != -1) {
        if (distances.contains(n)) {
            val one = distances[n]!!
            val max = maxOf(one, dist)
            if (max < min || max == min && n < res) {
                min = max
                res = n
            }
        }
        val tmp = edges[n]
        edges[n] = -1
        n = tmp
        dist++
    }
    return res
}