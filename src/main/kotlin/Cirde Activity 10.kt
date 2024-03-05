fun main() {
    print("Enter a number: ")
    val number = readLine()!!.toInt()

    var sequence = ""

    if (number % 2 == 0) {
        for (i in number downTo 2 step 2) {
            sequence += ", $i"
        }
        sequence = sequence.drop(2) // Remove the leading comma and space

    } else {
        for (i in number downTo 1 step 2) {
            sequence += ", $i"
        }
        sequence = sequence.drop(2) // Remove the leading comma and space

    }

    println("$number is ${if (number % 2 == 0) "Even" else "Odd"} -> $sequence")
}

/*

fun main() {
    print("Enter a number: ")
    val number = readLine()!!.toInt()

    if (number % 2 == 0) {
        println("$number is Even -> " + (number downTo 2 step 2).joinToString())
    } else {
        println("$number is Odd -> " + (number downTo 1 step 2).joinToString())
    }
}
 */
