fun main() {
    print("Enter an integer: ")
    val num = readLine()!!.toInt()

    var factorial = 1
    var expression = ""
    for (i in num downTo 1) {
        factorial *= i
        expression += if (i == 1) "$i" else "$i x "
    }

    println("Factorial of $num is: $expression = $factorial")
}
