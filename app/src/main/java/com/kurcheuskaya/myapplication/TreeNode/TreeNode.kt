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

/**
 *Given the root of a Binary Search Tree (BST), return the minimum
 *difference between the values of any two different nodes in the tree
 */

var diff = Int.MAX_VALUE
var prev: Int? = null
fun minDiffInBST(root: TreeNode?): Int {
    if (root == null) return Int.MAX_VALUE
    minDiffInBST(root.left)
    if (prev != null) {
        diff = minOf(diff, root.`val` - prev!!)
    }
    prev = root.`val`
    minDiffInBST(root.right)
    return diff
}

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 */
fun invertTree(root: TreeNode?): TreeNode? = root.let {
    val temp = it?.left
    it?.left = invertTree(it?.right)
    it?.right = invertTree(temp)
    it
}

/**
 * Given the root of a binary tree, return the zigzag level order traversal
 * of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 */
fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return listOf()
    var count = 0
    var currentList = listOf(root)
    val ans = mutableListOf<List<Int>>()
    while (currentList.isNotEmpty()) {
        if (count++ % 2 == 0) {
            ans.add(currentList.map { it.`val` })
        } else {
            ans.add(currentList.map { it.`val` }.reversed())
        }
        currentList = currentList.flatMap { listOfNotNull(it.left, it.right) }
    }
    return ans.toList()
}

/**
Given the root of a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the
root node of any one of them.
Two trees are duplicate if they have the same structure with the same node values.
 */
var res = mutableListOf<TreeNode?>()
fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
    if (root == null) return listOf(root)
    return check(root.left, root.right)
}

fun check(root1: TreeNode?, root2: TreeNode?): List<TreeNode?> {
    if (root1 == null || root2 == null) return res

    if (root1.`val` == root2.`val` && root1.left?.`val` == root2.left?.`val` && root1.right?.`val` == root2.left?.`val`) {
        res.add(root1)
        check(root1.right, root1.left)
        check(root2.right, root2.left)
    }
    return res
}