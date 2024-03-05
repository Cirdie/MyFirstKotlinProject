fun main() {
    println("Please Enter a Word:")
    var word = readLine()?.toLowerCase() ?: ""

    var isPalindrome = 1
    val length = word.length

    for (i in 0 until length / 2) {
        if (word[i] != word[length - i - 1]) {
            isPalindrome = 0
            break
        }
    }

    if (isPalindrome == 1) {
        println("The word is a palindrome.")
    } else {
        println("The word is not a palindrome.")
    }
}
