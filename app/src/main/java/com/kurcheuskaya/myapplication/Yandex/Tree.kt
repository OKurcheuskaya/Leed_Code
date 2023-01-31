package com.kurcheuskaya.myapplication.Yandex

import kotlin.math.abs


fun main() {
    val first = TreeNode(1)
    first.left = TreeNode(2)
    first.right = TreeNode(3)
    val second = TreeNode(1)
    second.left = TreeNode(2)
    second.right = TreeNode(3)
    println(isSameTree(first, second))
    var maxLevel = -1
    var res = -1
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p?.`val` != q?.`val`) return false
    return isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
}

fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) return true
    return mirrorTree(root.left, root.right)
}

fun mirrorTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p?.`val` != q?.`val`) return false
    return mirrorTree(p?.left, q?.right) && mirrorTree(p?.right, q?.left)
}

fun isBalanced(root: TreeNode?): Boolean {
    return getHeight(root) != -1
}

fun getHeight(root: TreeNode?): Int {
    if (root == null) return 0
    val leftHeight = getHeight(root.left)
    if (leftHeight == -1) return -1
    val rightHeight = getHeight(root.right)
    if (rightHeight == -1) return -1
    if (abs(leftHeight - rightHeight) > 1) return -1
    return maxOf(leftHeight, rightHeight) + 1
}


fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val currentPath = mutableListOf<Int>()
    pathSum(root, targetSum, currentPath, result)
    return result
}

private fun pathSum(
    node: TreeNode?,
    targetSum: Int,
    currentPath: MutableList<Int>,
    result: MutableList<List<Int>>
) {
    if (node == null) return
    currentPath.add(node.`val`)
    if (node.left == null && node.right == null && targetSum == node.`val`) {
        result.add(ArrayList(currentPath))
    } else {
        pathSum(node.left, targetSum - node.`val`, currentPath, result)
        pathSum(node.right, targetSum - node.`val`, currentPath, result)
    }
    currentPath.removeAt(currentPath.size - 1)
}



