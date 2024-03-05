fun main() {
    println("Enter an integer:")
    val input = readLine()?.toIntOrNull() ?: return

    var result = 1
    var expression = ""

    if (input < 0) {
        println("Factorial is not defined for negative numbers.")
        return
    }

    if (input == 0) {
        println("0! = 1")
        return
    }

    print("$input! = ")

    for (i in input downTo 2) {
        expression += "$i x " // Build expression string
        result *= i // Calculate factorial
    }

    println("${expression}1 = $result")
}
