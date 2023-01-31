package com.kurcheuskaya.myapplication.Yandex

fun main() {
    val first = TreeNode(1)
    first.left = TreeNode(2)
    first.right = TreeNode(3)
    val second = TreeNode(1)
    second.left = TreeNode(2)
    second.right = TreeNode(3)
    println(isSameTree(first, second))
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
