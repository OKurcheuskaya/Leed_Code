package com.kurcheuskaya.myapplication

import java.util.*

fun main(){
    val first = TreeNode(1)
    first.left = TreeNode(2)
    first.right = TreeNode(3)
    val second = TreeNode(1)
    second.left = TreeNode(2)
    second.right = TreeNode(3)
    println(isSameTree(first, second))
    println(preorderTraversal(first))
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
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

fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p?.`val` != q?.`val`) return false
    return  isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
}