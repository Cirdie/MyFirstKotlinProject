fun isPrime(num: Int): Boolean {
    if (num <= 1) return false
    for (i in 2 until num) {
        if (num % i == 0) return false
    }
    return true
}

fun main() {
    print("Enter the first integer: ")
    val num1 = readLine()!!.toInt()
    print("Enter the second integer: ")
    val num2 = readLine()!!.toInt()

    val minNum = if (num1 < num2) num1 else num2
    val maxNum = if (num1 < num2) num2 else num1

    println("Prime numbers between $minNum and $maxNum:")
    for (num in minNum..maxNum) {
        if (isPrime(num)) {
            println(num)
        }
    }
}
