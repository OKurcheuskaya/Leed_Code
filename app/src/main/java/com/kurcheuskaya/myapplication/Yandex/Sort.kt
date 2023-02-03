package com.kurcheuskaya.myapplication.Yandex

fun main() {

    println(
        merge(
            intervals = arrayOf(
                intArrayOf(1, 3),
                intArrayOf(2, 6),
                intArrayOf(8, 10),
                intArrayOf(15, 18)
            )
        )
    )
    println(
        merge(
            intervals = arrayOf(
                intArrayOf(1, 4),
                intArrayOf(4, 5)
            )
        )
    )
}
fun bubbleSort(arr: IntArray) {
    for (i in 0 until arr.size - 1) {
        for (j in 0 until arr.size - i - 1) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.isEmpty()) return arrayOf()
    intervals.sortBy { it[0] }
    val result = mutableListOf<IntArray>()
    var currentInterval = intervals[0]
    for (i in 1 until intervals.size) {
        val interval = intervals[i]
        if (currentInterval[1] >= interval[0]) {
            currentInterval[1] = maxOf(currentInterval[1], interval[1])
        } else {
            result.add(currentInterval)
            currentInterval = interval
        }
    }
    result.add(currentInterval)
    return result.toTypedArray()

}
fun quickSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = partition(arr, low, high)
        quickSort(arr, low, pivotIndex - 1)
        quickSort(arr, pivotIndex + 1, high)
    }
}

fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high]
    var i = low - 1
    for (j in low until high) {
        if (arr[j] <= pivot) {
            i++
            swap(arr, i, j)
        }
    }
    swap(arr, i + 1, high)
    return i + 1
}

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}