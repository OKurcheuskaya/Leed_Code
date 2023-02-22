package com.kurcheuskaya.myapplication.number

fun main() {

}

/**
 * 1011. Capacity To Ship Packages Within D Days

A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship
with packages on the conveyor belt (in the order given by weights). We may not load more weight than
the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt
being shipped within days days.
weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 */

fun shipWithinDays(weights: IntArray, days: Int): Int {
    var left = weights.maxOrNull() ?: -1
    var right = weights.sum()
    while (left < right) {
        val mid = (left + right) / 2
        var currentWeight = 0
        var netDays = 1
        for (w in weights) {
            if (currentWeight + w > mid) {
                netDays++
                currentWeight = 0
            }
            currentWeight += w
        }
        if (netDays > days) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return left
}
