fun main() {
    println("Enter the number of members:")
    val numOfMembers = readLine()?.toIntOrNull() ?: 0

    for (i in 1..numOfMembers) {
        println("Member $i:")

        println("Enter your first name:")
        val firstName = readLine() ?: ""

        println("Enter your last name:")
        val lastName = readLine() ?: ""

        val grades = mutableMapOf<String, Int>()

        var mathGrade: Int?
        do {
            println("Enter your Math grade:")
            mathGrade = readLine()?.toIntOrNull()
            if (mathGrade == null || mathGrade > 100) {
                println("Invalid grade for Math. Please try again.")
            }
        } while (mathGrade == null || mathGrade > 100)
        grades["Math"] = mathGrade

        var englishGrade: Int?
        do {
            println("Enter your English grade:")
            englishGrade = readLine()?.toIntOrNull()
            if (englishGrade == null || englishGrade > 100) {
                println("Invalid grade for English. Please try again.")
            }
        } while (englishGrade == null || englishGrade > 100)
        grades["English"] = englishGrade

        var filipinoGrade: Int?
        do {
            println("Enter your Filipino grade:")
            filipinoGrade = readLine()?.toIntOrNull()
            if (filipinoGrade == null || filipinoGrade > 100) {
                println("Invalid grade for Filipino. Please try again.")
            }
        } while (filipinoGrade == null || filipinoGrade > 100)
        grades["Filipino"] = filipinoGrade

        var scienceGrade: Int?
        do {
            println("Enter your Science grade:")
            scienceGrade = readLine()?.toIntOrNull()
            if (scienceGrade == null || scienceGrade > 100) {
                println("Invalid grade for Science. Please try again.")
            }
        } while (scienceGrade == null || scienceGrade > 100)
        grades["Science"] = scienceGrade

        val average = grades.values.average()

        println("\nName: $firstName $lastName")
        println("Math Grade: ${grades["Math"]}")
        println("English Grade: ${grades["English"]}")
        println("Filipino Grade: ${grades["Filipino"]}")
        println("Science Grade: ${grades["Science"]}")
        println("Average Grade: $average\n")
    }
}
