package com.kurcheuskaya.myapplication.Yandex

fun main() {
    println(maxProfit(prices = intArrayOf(7, 1, 5, 3, 6, 4)))
    println(maxProfitSameDay(prices = intArrayOf(7, 1, 5, 3, 6, 4)))
    println(maxProfitFee( prices = intArrayOf(1,3,2,8,4,9), fee = 2))
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
            maxProfit += prices[i] - prices[i - 1]
        }
    }
    return maxProfit
}

fun maxProfitFee(prices: IntArray, fee: Int): Int {
    var cash = 0
    var hold = -prices[0]
    for (i in 1 until prices.size) {
        cash = maxOf(cash, hold + prices[i] - fee)
        hold = maxOf(cash - prices[i], hold)
    }
    return cash
}