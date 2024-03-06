fun main() {
    print("Enter a string: ")
    val userString = readLine() ?: ""

    val isPalindrome = checkPalindrome(userString)
    printResult(isPalindrome, userString)
}

fun checkPalindrome(inputStr: String): Boolean {
    val inputStrLower = inputStr.toLowerCase()
    return inputStrLower == inputStrLower.reversed()
}

fun printResult(isPalindrome: Boolean, inputStr: String) {
    if (isPalindrome) {
        println("$inputStr: Palindrome")
    } else {
        println("$inputStr: Not Palindrome")
    }
}
