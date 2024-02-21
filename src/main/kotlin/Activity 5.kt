fun main() {
    // Declare variables for student information
    var firstName: String
    var middleName: String
    var lastName: String
    var studentId: Int
    
    // Declare variables for course and year level
    var course: String
    var yearLevel: String
    
    // Declare variables for book borrowing
    var numBooksToBorrow: Int
    var borrowMore: String
    
    // Welcome message
    println("===== School Library System =====")
    
    // Input student information
    println("Please Enter your First Name:")
    firstName = readLine()!!

    println("Please Enter your Middle Name:")
    middleName = readLine()?.substring(0, 1) ?: ""

    println("Please Enter your Last Name:")
    lastName = readLine()!!

    println("Please Enter your Student Id:")
    studentId = readLine()?.toIntOrNull() ?: 0
    
    // Input course information
    println("Select a Number to choose your Course")
    println("[1] BSIT")
    println("[2] BSHM")
    println("[3] BSED")
    println("[4] BSBA")
    var selectedCourse: Int
    do {
        println("Enter the number corresponding to your course:")
        selectedCourse = readLine()?.toIntOrNull() ?: 0
        if (selectedCourse !in 1..4) {
            println("Error: Invalid input. Please enter a number between 1 and 4.")
        }
    } while (selectedCourse !in 1..4)

    course = when (selectedCourse) {
        1 -> "BSIT"
        2 -> "BSHM"
        3 -> "BSED"
        4 -> "BSBA"
        else -> "Unknown"
    }
    
    // Input year level information
    println("Select a Number to choose Student Year Level")
    println("[1] First Year")
    println("[2] Second Year")
    println("[3] Third Year")
    println("[4] Fourth Year")
    var selectedYearLevel: Int
    do {
        println("Enter the number corresponding to your year level:")
        selectedYearLevel = readLine()?.toIntOrNull() ?: 0
        if (selectedYearLevel !in 1..4) {
            println("Error: Invalid input. Please enter a number between 1 and 4.")
        }
    } while (selectedYearLevel !in 1..4)

    yearLevel = when (selectedYearLevel) {
        1 -> "First Year"
        2 -> "Second Year"
        3 -> "Third Year"
        4 -> "Fourth Year"
        else -> "Unknown"
    }
    
    // Input number of books to borrow
    println("Okay $lastName, Indicate how many books you are going to borrow:")
    numBooksToBorrow = readLine()?.toIntOrNull() ?: 0
    
    var currentBookNumber = 1
    // Borrowing books loop
    while (currentBookNumber <= numBooksToBorrow) {
        println("==================================================")
        println("Books Left to Borrow: ${numBooksToBorrow - currentBookNumber + 1}")
        println("Book Number: $currentBookNumber")
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
            println("Student ID: $studentId")
            println("Course: $course")
            println("Year Level: $yearLevel")
            println("Book Title: $bookTitle")
            println("Book Author: $bookAuthor")
            currentBookNumber++
        } else {
            println("Book borrowing cancelled for Book Number $currentBookNumber")
            break
        }
    }

    // Asking if user wants to borrow more books
    borrowMore = "Yes"
    while (borrowMore != "2") {
        println("Do you want to borrow more books?")
        println("[1] Yes")
        println("[2] No")
        borrowMore = readLine() ?: ""
        if (borrowMore == "1") {
            println("Indicate how many More Books you want to borrow:")
            val moreBooks = readLine()?.toIntOrNull() ?: 0
            numBooksToBorrow += moreBooks
            currentBookNumber = numBooksToBorrow - moreBooks + 1 // Update current book number
            for (i in 1..moreBooks) {
                println("==================================================")
                println("Books Left to Borrow: ${moreBooks - i + 1}")
                println("Book Number: ${currentBookNumber + i - 1}") // Incremental book number
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
                    println("Student ID: $studentId")
                    println("Course: $course")
                    println("Year Level: $yearLevel")
                    println("Book Title: $bookTitle")
                    println("Book Author: $bookAuthor")
                } else {
                    println("Book borrowing cancelled for Book Number ${currentBookNumber + i - 1}")
                    break
                }
            }
        } else if (borrowMore == "2") {
            println("Thank you for using the School Library System.")
        }
    }
}
