fun main() {
    println("Enter First Character:")
    val character1 = readLine()?.toLowerCase()?.filterNot { it == ' ' } ?: ""

    println("Enter Second Character:")
    val character2 = readLine()?.toLowerCase()?.filterNot { it == ' ' } ?: ""

    var uniqueChars = ""

    for (c in character1 + character2) {
        if (!uniqueChars.contains(c) && (c !in character1 || c !in character2)) {
            uniqueChars += c
        }
    }

    if (uniqueChars.isEmpty() || character1 == character2) {
        println("No Unique Character")
    } else {
        println("Unique Characters are")
        var index = 1
        for (char in uniqueChars) {
            println("[$index] $char")
            index++
        }
    }
}
