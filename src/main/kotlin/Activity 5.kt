fun main() {
    var firstName: String
    var middleName: String
    var lastName: String
    var studentId: Int
    var course: String
    var yearLevel: String
    var numBooks: Int
    var borrowMore: String

    println("===== School Library System =====")
    println("Please Enter your First Name:")
    firstName = readLine()!!

    println("Please Enter your Middle Name:")
    middleName = readLine()?.substring(0, 1) ?: ""

    println("Please Enter your Last Name:")
    lastName = readLine()!!

    println("Please Enter your Student Id:")
    studentId = readLine()?.toIntOrNull() ?: 0

    println("Select a Number to choose your Course")
    println("[1] BSIT")
    println("[2] BSHM")
    println("[3] BSED")
    println("[4] BSBA")
    var courseNumber: Int
    do {
        println("Enter the number corresponding to your course:")
        courseNumber = readLine()?.toIntOrNull() ?: 0
        if (courseNumber !in 1..4) {
            println("Error: Invalid input. Please enter a number between 1 and 4.")
        }
    } while (courseNumber !in 1..4)

    course = when (courseNumber) {
        1 -> "BSIT"
        2 -> "BSHM"
        3 -> "BSED"
        4 -> "BSBA"
        else -> "Unknown"
    }

    println("Select a Number to choose Student Year Level")
    println("[1] First Year")
    println("[2] Second Year")
    println("[3] Third Year")
    println("[4] Fourth Year")
    var yearNumber: Int
    do {
        println("Enter the number corresponding to your year level:")
        yearNumber = readLine()?.toIntOrNull() ?: 0
        if (yearNumber !in 1..4) {
            println("Error: Invalid input. Please enter a number between 1 and 4.")
        }
    } while (yearNumber !in 1..4)

    yearLevel = when (yearNumber) {
        1 -> "First Year"
        2 -> "Second Year"
        3 -> "Third Year"
        4 -> "Fourth Year"
        else -> "Unknown"
    }

    println("Okay $lastName, Indicate how many books you are going to borrow:")
    numBooks = readLine()?.toIntOrNull() ?: 0

    borrowMore = "Yes"
    while (borrowMore != "2") {
        var currentBookNumber = 1
        for (i in 1..numBooks) {
            println("==================================================")
            println("Book To Borrow Left ${numBooks - i + 1}")
            println("Book Number $currentBookNumber")
            println("Enter Book Title:")
            val bookTitle = readLine()!!

            println("Enter Book Author:")
            val bookAuthor = readLine()!!

            println("Are you sure you want to Borrow this Book?")
            println("Book Title: $bookTitle")
            println("Book Author: $bookAuthor")
            println("[1] Yes")
            println("[2] No")

            var confirm: String
            do {
                confirm = readLine() ?: ""
                if (confirm != "1" && confirm != "2") {
                    println("Invalid input. Please enter 1 or 2.")
                }
            } while (confirm != "1" && confirm != "2")

            if (confirm == "1") {
                println("===== Book Borrowed Successfully =====")
                println("Student Borrower: $firstName $middleName $lastName")
                println("Book Title: $bookTitle")
                println("Book Author: $bookAuthor")
                currentBookNumber++
            } else {
                println("Book borrowing cancelled for Book Number $currentBookNumber")
                break
            }
        }

        println("Do you want to borrow more books?")
        println("[1] Yes")
        println("[2] No")
        borrowMore = readLine() ?: ""
        if (borrowMore == "1") {
            println("Indicate how many More Books you want to borrow:")
            val moreBooks = readLine()?.toIntOrNull() ?: 0
            numBooks += moreBooks
        } else if (borrowMore == "2") {
            println("Thank you for using the School Library System.")
        }
    }
}
