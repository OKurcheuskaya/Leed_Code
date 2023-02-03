package com.kurcheuskaya.myapplication.Yandex

fun main() {
    println(maxProfit(prices = intArrayOf(7, 1, 5, 3, 6, 4)))
    println(maxProfitSameDay(prices = intArrayOf(7, 1, 5, 3, 6, 4)))
}

fun maxProfit(prices: IntArray): Int {
    var min = prices[0]
    var maxProfit = 0
    for (price in prices) {
        if (price < min) {
            min = price
        } else {
            maxProfit = maxOf(maxProfit, price - min)
        }

    }
    return maxProfit
}

//[7,1,5,3,6,4]
fun maxProfitSameDay(prices: IntArray): Int {
    var maxProfit = 0
    for (i in 1 until prices.size) {
        if (prices[i] > prices[i - 1]) {
            maxProfit += prices[i]-prices[i-1]
        }
    }
    return maxProfit
}