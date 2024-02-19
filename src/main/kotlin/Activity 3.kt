fun main() {
    println("Enter the number of group members:")
    val numOfMembers = readLine()?.toIntOrNull()

    if (numOfMembers == null || numOfMembers <= 0) {
        println("Invalid input!! Please enter a valid number of group members!!")
        return
    }

    for (i in 1..numOfMembers) {
        println("Enter name:")
        val name = readLine() ?: ""

        println("Enter age:")
        val ageInput = readLine()
        val age = ageInput?.toIntOrNull()

        if (age == null || age <= 0) {
            println("Invalid age!! Please enter a valid age!!")
            return
        }

        val ageGroup = if (age in 1..17) {
            "Under age"
        } else if (age in 18..30) {
            "Young Adults"
        } else if (age in 31..59) {
            "Adult"
        } else {
            "Senior"
        }

        println("$name belongs to the $ageGroup group.")
    }
}

//Other Method

/*

fun main() {
    println("Enter the number of group members:")
    val numOfMembers = readLine()!!.toInt()

    repeat(numOfMembers) {
        println("Enter name:")
        val name = readLine()!!
        println("Enter age:")
        val age = readLine()!!.toInt()

        val ageGroup = when {
            age in 1..17 -> "underage"
            age in 18..30 -> "young adult"
            age in 31..59 -> "adult"
            age >= 60 -> "senior"
            else -> "Invalid age"
        }

        println("$name belongs to the $ageGroup group.")
    }
}
*/
