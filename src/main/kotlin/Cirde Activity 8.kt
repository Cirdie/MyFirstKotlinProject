fun main() {
    println("Enter one or two integers separated by a comma (e.g., 1, 4 or 10):")
    val input = readLine() ?: ""

    val splitInput = input.split(",").map { it.trim().toIntOrNull() }.filterNotNull()

    if (splitInput.isEmpty()) {
        println("Invalid input. Please enter valid integers.")
        return
    }

    val lower = splitInput.minOrNull() ?: 0
    val higher = splitInput.maxOrNull() ?: 0

    val range = if (splitInput.size == 1) 1..higher else lower..higher

    println("Prime numbers are:")
    for (number in range) {
        var isPrime = 1
        if (number <= 1) {
            isPrime = 0
        } else {
            for (i in 2 until number) {
                if (number % i == 0) {
                    isPrime = 0
                    break
                }
            }
        }
        if (isPrime == 1) {
            println(number)
        }
    }
}
