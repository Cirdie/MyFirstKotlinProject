fun main() {
    print("Pls enter a Number:")
    val num = readLine()?.toInt() ?: 0
    displayNumber(num)
}
fun displayNumber(num: Int) {
    if (num % 2 == 0) {
        println("$num is even.")
        println("Even numbers:")
        for (i in num downTo 2 step 2)
            println(i)
    }else{
        println("$num is odd.")
        println("Odd numbers:")
        for(i in num downTo 1 step 2)
                println(i)

}

}
