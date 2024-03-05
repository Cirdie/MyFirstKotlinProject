fun main() {
  
    println("Enter a percentage:")
    val percentage = readLine()!!.toDouble() / 100 // Convert percentage to decimal

    println("Enter a value to get a percentage:")
    val value = readLine()!!.toDouble()
    
    val result = percentage * value

    println("Answer:")
    println("%.5f".format(result))
}
