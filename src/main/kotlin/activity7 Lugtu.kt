fun main() {
    print("Enter a string: ")
    val userString = readLine() ?: ""

    val isPalindrome = checkPalindrome(userString)
    printResult(isPalindrome, userString)
}

fun checkPalindrome(inputStr: String): Boolean {
    val inputStrLower = inputStr.toLowerCase()
    val length = inputStrLower.length
    for (i in 0 until length / 2) {
        if (inputStrLower[i] != inputStrLower[length - i - 1]) {
            return false
        }
    }
    return true
}

fun printResult(isPalindrome: Boolean, inputStr: String) {
    if (isPalindrome) {
        println("$inputStr: Palindrome")
    } else {
        println("$inputStr: Not Palindrome")
    }
}
