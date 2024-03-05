fun main() {
    println("Enter First Character:")
    val character1 = readLine()?.toLowerCase() ?: ""

    println("Enter Second Character:")
    val character2 = readLine()?.toLowerCase() ?: ""

    var uniqueChars = ""

    for (c1 in character1) {
        var found = false // Initialize a variable to track if character is found in 'character2'

        for (c2 in character2) {
            if (c1 == c2) {
                found = true // Set 'found' to true if character is found
                break // Exit the loop as the character is already found
            }
        }

        if (!found && !uniqueChars.contains(c1)) {
            uniqueChars += c1
        }
    }

    for (c2 in character2) {
        var found = false // Initialize a variable to track if character is found in 'character1'

        for (c1 in character1) {
            if (c2 == c1) {
                found = true // Set 'found' to true if character is found
                break // Exit the loop as the character is already found
            }
        }

        if (!found && !uniqueChars.contains(c2)) {
            uniqueChars += c2
        }
    }

    if (uniqueChars.isEmpty() || character1 == character2) {
        println("No Unique Character")
    } else {
        println("Unique Characters are")
        var index = 1 // Initialize index counter for printing
        for (c in uniqueChars) {
            println("[$index] $c")
            index++
        }
    }
}
