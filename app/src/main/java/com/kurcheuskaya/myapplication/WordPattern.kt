package com.kurcheuskaya.myapplication

fun main() {
    println(wordPattern(pattern = "abba", s = "dog cat cat dog"))
    println(gcdOfStrings(str1 = "ABCABCABC", str2 = "ABCABC"))
    println(
        findAllConcatenatedWordsInADict(
            words = arrayOf(
                "a", "b","ab", "abc"
            )
        )
    )
}

fun wordPattern(pattern: String, s: String): Boolean {
    val patternArray = pattern.toCharArray()
    val stringArray = s.split(" ")

    if (patternArray.size != stringArray.size)
        return false

    val map = hashMapOf<Char, String>()
    for (i in patternArray.indices) {
        val temp = map[patternArray[i]]
        if (temp.isNullOrEmpty()) {
            if (map.containsValue(stringArray[i])) return false
            map[patternArray[i]] = stringArray[i]
        }
        if (map[patternArray[i]] != stringArray[i]) return false

    }
    return true
}

fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
    val wordsSet = words.toMutableSet()
    val cache = mutableMapOf<String, Boolean>()

    return words.filter {
        wordsSet.remove(it)
        val result = wordsSet.isConcatenatedWord(it, cache)
        wordsSet.add(it)
        result
    }
}
fun gcdOfStrings(str1: String, str2: String): String {
    if (str1 + str2 != str2 + str1) return ""
    val gcdVal = gcd(str1.length, str2.length)
    return str2.substring(0, gcdVal)
}

fun gcd(p: Int, q: Int): Int {
    return if (q == 0) p else
        gcd(q, p % q)
}

fun isAlienSorted(words: Array<String>, order: String): Boolean {
    val map = HashMap<Char, Int>()
    for (i in order.indices) {
        map[order[i]] = i
    }
    for (i in 1 until words.size) {
        var word1 = words[i - 1]
        var word2 = words[i]
        var j = 0
        while (j < word1.length && j < word2.length) {
            if (word1[j] != word2[j]) {
                if (map[word1[j]]!! > map[word2[j]]!!) {
                    return false
                }
                break
            }
            j++
        }
        if (j == word2.length && word1.length > word2.length) {
            return false
        }
    }
    return true
}

private fun Set<String>.isConcatenatedWord(
    word: String,
    cache: MutableMap<String, Boolean>
): Boolean {
    if(word in this) {
        return true
    }

    if(word in cache) {
        return cache[word]!!
    }

    for(i in word.indices) {
        val left = word.substring(0, i+1)
        val right = word.substring(i+1)

        if(left in this && isConcatenatedWord(right, cache)) {
            cache[word] = true
            return true
        }
    }

    cache[word] = false
    return false
}