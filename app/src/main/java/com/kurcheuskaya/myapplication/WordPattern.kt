package com.kurcheuskaya.myapplication

fun main() {
    println(wordPattern(pattern = "abba", s = "dog cat cat dog"))
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