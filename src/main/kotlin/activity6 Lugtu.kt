fun main() {
    print("Pls enter your first string:")
    val str1 = readLine()!!
    print("Pls enter your second String:")
    val str2 = readLine()!!
    uniqueCharacters(str1, str2)
}
fun uniqueCharacters(str1: String, str2: String) {
    val combine = str1 + str2
    println("Unique character in both strings:")

    val uniqueChars = combine.groupBy {it}

        .filter { it.value.size == 1}
        .keys
    if (uniqueChars.isEmpty()) {
        print("There  are no unique characters in both string.")
    }else{
        uniqueChars.forEach {
            println(it)}
        }

    }
