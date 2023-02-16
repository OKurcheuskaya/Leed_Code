package com.kurcheuskaya.myapplication

import java.util.*

fun main() {
    val first = TreeNode(1)
    first.left = TreeNode(2)
    first.right = TreeNode(3)
    val second = TreeNode(1)
    second.left = TreeNode(2)
    second.right = TreeNode(3)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
fun maxDepth(root: TreeNode?): Int {
    if (root?.`val` == null) return 0
    val left = maxDepth(root.left)
    val right = maxDepth(root.right)
    return maxOf(left, right) + 1
}
fun preorderTraversal(root: TreeNode?): List<Int> {
    val list = arrayListOf<Int>()
    val stack = Stack<TreeNode>()
    var current = root
    while (current != null || stack.isNotEmpty()) {
        while (current != null) {
            list.add(root!!.`val`)
            stack.push(current)
            current = current.left
        }
        current = stack.pop()
        current = current.right
    }
    return list
}

fun stackPush(): List<Int> {
    val stack = Stack<Int>()
    stack.push(5)
    stack.push(2)
    stack.pop()
    stack.push(5)
    stack.push(0)
    stack.pop()
    stack.pop()
    stack.pop()
    stack.push(1)
    return stack
}

fun minTime(n: Int, edges: List<List<Int>>, hasApple: BooleanArray): Int {
    val visited = BooleanArray(n)
    val graph = Array(n) { mutableListOf<Int>() }

    edges.forEach { (s, d) ->
        graph[s].add(d)
        graph[d].add(s)
    }

    fun dfs(vertices: MutableList<Int>, i: Int): Int {
        if (visited[i]) {
            return 0
        }
        visited[i] = true

        var total = 0
        vertices.forEach { v ->
            total += dfs(graph[v], v)
        }
        if (i != 0 && (hasApple[i] || total != 0)) {
            total += 2
        }
        return total
    }

    return dfs(graph[0], 0)
}

private lateinit var result: IntArray
fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
    result = IntArray(n)
    val graph: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    for (i in 0 until n) graph[i] = mutableListOf()
    edges.forEach { edge ->
        graph[edge[0]]!!.add(edge[1])
        graph[edge[1]]!!.add(edge[0])
    }
    dfs(0, graph, BooleanArray(n), labels)
    return result
}

fun dfs(current: Int, graph: Map<Int, List<Int>>, visited: BooleanArray, labels: String): IntArray {
    // mark this node as visited.
    visited[current] = true
    // initialize child-label-counter.
    val chars = IntArray(26)
    // iterate childs.
    graph[current]!!.forEach { child ->
        if (!visited[child]) {
            // if not visited, get child's accumulated label-count
            val count = dfs(child, graph, visited, labels)
            for (i in 0 until 26) chars[i] += count[i]
        }
    }
    // add current word to label-counter and pass bottom-up.
    result[current] = ++chars[labels[current] - 'a']
    return chars
}

// roads = [[0,1],[0,2],[0,3]], seats = 5
//  roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
data class R(val cars: Long, val capacity: Int, val fuel: Long)
fun minimumFuelCost(roads: Array<IntArray>, seats: Int): Long {
    val nodes = mutableMapOf<Int, MutableList<Int>>()
    roads.forEach { (from, to) ->
        nodes.getOrPut(from, { mutableListOf() }) += to
        nodes.getOrPut(to, { mutableListOf() }) += from
    }
    fun dfs(curr: Int, parent: Int): R {
        val children = nodes[curr]
        if (children == null) return R(1L, seats - 1, 0L)
        var fuel = 0L
        var capacity = 0
        var cars = 0L
        children.filter { it != parent }.forEach {
            val r = dfs(it, curr)
            fuel += r.cars + r.fuel
            capacity += r.capacity
            cars += r.cars
        }
        // seat this passenger
        if (capacity == 0) {
            cars++
            capacity = seats - 1
        } else capacity--
        // optimize cars
        while (capacity - seats >= 0) {
            capacity -= seats
            cars--
        }
        return R(cars, capacity, fuel)
    }
    return dfs(0, 0).fuel
}