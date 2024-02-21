fun main(){

    println("Enter student name:")
    val studentName = readLine()!!

    println("Enter Student Id:")
    val studentID = readLine()!!

    println("Enter student course:")
    val studentCourse = readLine()!!

    var studentYearLevel = ""
    do{
        println("Choose student Year Level (1-4):")
        println("[1] First Year ")
        println("[2] Second Year")
        println("[3] Third Year")
        println("[4] Fourth Year")
        val yearInput = readLine()!!.toIntOrNull()
        studentYearLevel = when(yearInput){
            1 -> "First Year"
            2 -> "Second Year"
            3 -> "Third Year"
            4 -> "Fourth Year"
            else -> {
                println("Invalid input. Please choose a valid year level.")
                continue
            }
        }
        break
    }while (true)
    println("Enter the number of books to borrow:")
    val numberBooksToBorrow = readLine()!!.toInt()

    repeat(numberBooksToBorrow){
        println("Enter book Title:")
        val bookTitle = readLine()!!

        println("Enter book Author:")
        val bookAuthor = readLine()!!

        println("Enter number of copies available:")
        val copiesAvailable = readLine()!!

        val canBeBorrowed = copiesAvailable > 0.toString()

        println("Student:$studentName (ID: $studentID, Course: $studentCourse, Year Level: $studentYearLevel")
        println("Book details - Title: $bookTitle, Author:$bookAuthor")

        var userConfirmation:String

        do{
            println("Are you sure you want to borrow this book? (yes/no)")
            userConfirmation = readLine()!!.toLowerCase()

            if(userConfirmation == "yes"){
                if(canBeBorrowed){
                    println("Book successfully borrowed.")
                }else{
                    println("Sorry, this book is not available for borrowing.")
                }
            }else{
                println("Book borrowing cancelled.")
            }
        }while(userConfirmation != "yes" && userConfirmation != "no")


        println("Do you want to borrow more books? (yes/no)")
        val continueBorrowing = readLine()!!.toLowerCase()
        if (continueBorrowing == "no"){
        }
    }

}
