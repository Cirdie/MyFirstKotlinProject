import java.time.LocalDate
import java.util.logging.Logger
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


data class Book(
        val title: String,
        val author: String,
        val genre: String,
        val publicationDate: LocalDate,
        var borrower: Borrower? = null,
        var returnDate: LocalDate? = null
)

data class Borrower(
        val firstName: String,
        val middleName: String,
        val lastName: String,
        val course: String,
        val schoolID: String,
        val borrowDate: LocalDate,
        val returnDate: LocalDate
)



val booksAvailable = mutableListOf<Book>()
val booksBorrowed = mutableListOf<Book>()

fun main() {
    logInfo("Starting the Library System")

    println("=".repeat(10) + " WELCOME TO LIBRARY SYSTEM " + "=".repeat(10))

    while (true) {
        println("\nSelect a number to choose an option")
        println("[1] Add a book")
        println("[2] Borrow a book")
        println("[3] Exit\n")
        print("Please enter a number your choice: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                logInfo("User selected option: Add a book")
                addBookMenu()
            }
            2 -> {
                logInfo("User selected option: Borrow a book")
                borrowBookMenu()
            }
            3 -> {
                logInfo("User selected option: Exit")
                println("Exiting the Library System. Goodbye!")
                return
            }
            else -> {
                logError("Invalid input. Please enter a number from 1 to 3.")
                println("Invalid input. Please enter a number from 1 to 3.")
            }
        }
    }
}

fun addBookMenu() {
    logInfo("Entering Add Book Menu")

    println("")
    println("=".repeat(47))
    Thread.sleep(2000)
    while (true) {
        println("\n[ADD A BOOK MENU]\n")
        println("[1] Add Book to Library")
        println("[2] Remove Book from Library")
        println("[3] List Books Available")
        println("[4] List Books Borrowed")
        println("[5] Update Book Information")
        println("[6] Exit Add Book Menu\n")
        print("Please enter a number your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                logInfo("User selected option: Add Book to Library")
                addBook()
            }
            2 -> {
                logInfo("User selected option: Remove Book from Library")
                removeBook()
            }
            3 -> {
                logInfo("User selected option: List Books Available")
                listAvailableBooks()
            }
            4 -> {
                logInfo("User selected option: List Books Borrowed")
                listBorrowedBooks()
            }
            5 -> {
                logInfo("User selected option: Update Book Information")
                updateBook()
            }
            6 -> {
                logInfo("User selected option: Exit Add Book Menu")
                return
            }
            else -> {
                logError("Invalid input. Please enter a number from 1 to 6.")
                println("Invalid input. Please enter a number from 1 to 6.")
            }
        }
    }
}
fun addBook() {
    logger.info("Adding a book to the library.")

    println("")
    println("=".repeat(47))

    println("\n[ADDING BOOK TO LIBRARY]")
    println("\nPlease provide the following information for adding a new book\n")
    print("Book Title: ")
    val title = readLine() ?: ""
    print("Book Author: ")
    val author = readLine() ?: ""
    print("Book Genre: ")
    val genre = readLine() ?: ""

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var publicationDate: LocalDate? = null

    do {
        print("Publication Date (yyyy-MM-dd): ")
        val publicationDateStr = readLine() ?: ""
        try {
            publicationDate = LocalDate.parse(publicationDateStr, formatter)

        } catch (e: DateTimeParseException) {
            println("Error!! Please enter the publication date in the format yyyy-MM-dd !!")
            logger.warning("Invalid publication date format entered by the user.")
        }
    } while (publicationDate == null)

    val newBook = Book(title, author, genre, publicationDate)
    val existingBook = booksAvailable.find { it.title == newBook.title }

    if (existingBook != null) {
        println("Sorry The book [$title] is already exists in the library!!")
        logger.warning("Attempted to add a book that already exists in the library: $title")
    } else {
        println("\nAre you sure you want to add this Book?\n")

        println("%-4s| %-30s| %-20s| %-20s| %-15s".format("No", "Book Title", "Author", "Genre", "Publication Date"))
        println("-".repeat(100))
        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(1, title, author, genre, publicationDate))

        println("\n[1] Yes")
        println("[2] No")

        print("\nEnter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                booksAvailable.add(newBook)
                println("Book added successfully.")
                logger.info("Book added successfully: $title")
            }
            2 -> println("Adding Book canceled.")
            else -> {
                println("Invalid Input Adding Book Cancelled")
                logger.warning("Invalid input received while adding a book.")
            }
        }
    }
}

fun removeBook() {
    logger.info("Removing a book from the library.")

    println("")
    println("=".repeat(47))

    println("\n[REMOVE BOOK FROM THE LIBRARY]")

    // Check if there are any books available in the library
    if (booksAvailable.isEmpty()) {
        println("Sorry, no books available in the library to remove.")
        logger.warning("Attempted to remove a book from an empty library.")
        return
    }

    print("Enter Book title to Remove: ")
    val title = readLine() ?: ""

    val book = booksAvailable.find { it.title.equals(title, ignoreCase = true) }

    println("%-4s| %-30s| %-20s| %-20s| %-15s".format("No", "Book Title", "Author", "Genre", "Publication Date"))
    println("-".repeat(100))

    if (book != null) {
        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(1, book.title, book.author, book.genre, book.publicationDate))

        println("\nAre you sure you want to remove this Book?")
        println("[1] Yes")
        println("[2] No")

        print("\nEnter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                booksAvailable.remove(book)
                println("Book removed successfully.")
                logger.info("Book removed successfully: $title")
            }
            2 -> println("Removal canceled.")
            else -> {
                println("Invalid input. Removal canceled.")
                logger.warning("Invalid input received while removing a book.")
            }
        }
    } else {
        println("Book not found.")
        logger.warning("Attempted to remove a book that does not exist: $title")
    }
}


fun listAvailableBooks() {
    logger.info("Listing available books in the library.")

    println("")
    println("=".repeat(47))

    println("\n[LIST BOOKS AVAILABLE IN LIBRARY]")
    if (booksAvailable.isEmpty()) {
        println("Sorry, no books available in the library.")
        logger.warning("Attempted to list available books from an empty library.")
        return
    }

    println("\nThe List of Books Available in Library")
    println("%-4s| %-30s| %-20s| %-20s| %-15s".format("No", "Book Title", "Author", "Genre", "Publication Date"))
    println("-".repeat(100))

    booksAvailable.forEachIndexed { index, book ->
        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(index + 1, book.title, book.author, book.genre, book.publicationDate))
    }
    logger.info("Listed available books successfully.")
}

fun listBorrowedBooks() {
    logger.info("Listing borrowed books in the library.")

    println("")
    println("=".repeat(47))

    println("\n[LIST BOOKS BORROWED IN LIBRARY]")
    if (booksBorrowed.isEmpty()) {
        println("Sorry, no books borrowed from the library.")
        logger.warning("Attempted to list borrowed books from an empty library.")
        return
    }

    println("\nBorrowed Books:")
    println("%-4s| %-30s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s".format(
            "No", "Book Title", "Author", "Genre", "Publication Date", "Borrower", "Course", "School ID", "Return Date"
    ))
    println("-".repeat(150))

    booksBorrowed.forEachIndexed { index, book ->
        val borrower = book.borrower
        val borrowerInfo = if (borrower != null) {
            "${borrower.firstName} ${borrower.middleName.take(1)}. ${borrower.lastName}"
        } else {
            "Unknown"
        }
        val course = borrower?.course ?: "Unknown"
        val schoolID = borrower?.schoolID ?: "Unknown"
        val returnDate = book.returnDate ?: "Unknown"

        println("%-4d| %-30s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s".format(
                index + 1, book.title, book.author, book.genre, book.publicationDate, borrowerInfo, course, schoolID, returnDate
        ))
    }
    logger.info("Listed borrowed books successfully.")
}


// This function allows the user to update the information of a book in the library.
fun updateBook() {
    logger.info("Updating books in the library.")

    println("")
    println("=".repeat(47))

    println("\n[UPDATING BOOKS IN LIBRARY]")
    println("\nPlease provide the following information to update a book")
    print("Please Enter the title of the book you want to update: ")
    val titleToUpdate = readLine() ?: "" // Prompting the user to enter the title of the book to be updated.

    // Finding the book to update based on the provided title.
    val bookToUpdate = booksAvailable.find { it.title.equals(titleToUpdate, ignoreCase = true) }
    if (bookToUpdate == null) {
        println("Sorry, the book with the title '$titleToUpdate' does not exist in the library.")
        logger.warning("Attempted to update a non-existent book with title: $titleToUpdate")
        return
    }

    // Displaying the current information of the book to be updated.
    println("\nCurrent Book Information:")
    println("%-4s| %-30s| %-20s| %-20s| %-15s".format("No", "Book Title", "Author", "Genre", "Publication Date"))
    println("-".repeat(100))

    // Printing the details of the book to be updated.
    println("%-4d| %-30s| %-20s| %-20s| %-15s".format(1, bookToUpdate.title, bookToUpdate.author, bookToUpdate.genre, bookToUpdate.publicationDate))

    // Prompting the user to enter the updated information for the book.
    println("\nPlease provide the updated information for the book")
    print("New Title (Press Enter to keep existing title): ")
    val newTitle = readLine()?.takeUnless { it.isBlank() } ?: bookToUpdate.title
    print("New Author (Press Enter to keep existing author): ")
    val newAuthor = readLine()?.takeUnless { it.isBlank() } ?: bookToUpdate.author
    print("New Genre (Press Enter to keep existing genre): ")
    val newGenre = readLine()?.takeUnless { it.isBlank() } ?: bookToUpdate.genre

    val formatter = DateTimeFormatter.ofPattern("yyy-MM-dd")
    var newPublicationDate: LocalDate? = null

    // Prompting the user to enter the new publication date, or keep the existing one.
    do {
        print("New Publication Date (MM-dd-yyyy, Press Enter to keep existing publication date): ")
        val newPublicationDateStr = readLine()
        if (newPublicationDateStr.isNullOrBlank()) {
            newPublicationDate = bookToUpdate.publicationDate // Keep existing publication date
            break
        }
        try {
            newPublicationDate = LocalDate.parse(newPublicationDateStr, formatter)
        } catch (e: DateTimeParseException) {
            println("Error: Please enter the publication date in the format MM-dd-yyyy.")
        }
    } while (newPublicationDate == null)

    // Creating a new Book object with the updated information.
    val updatedBook = Book(newTitle, newAuthor, newGenre, newPublicationDate ?: bookToUpdate.publicationDate)
    booksAvailable.remove(bookToUpdate) // Removing the old book from the list.
    booksAvailable.add(updatedBook) // Adding the updated book to the list.

    println("Book updated successfully.") // Printing a success message after the book has been updated.
    logger.info("Book with title '$titleToUpdate' updated successfully.")
}

fun borrowBookMenu() {
    logger.info("Entering borrow book menu.")

    println("")
    println("=".repeat(47))
    Thread.sleep(3000)

    while (true) {
        println("\n[BORROW A BOOK MENU]\n")
        println("[1] Search Book")
        println("[2] List Available")
        println("[3] List Borrowed Book")
        println("[4] Borrow Book")
        println("[5] Exit Borrowing Menu\n")
        print("Enter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> searchBook()
            2 -> listAvailableBooks()
            3 -> listBorrowedBooks()
            4 -> borrowBook()
            5 -> {
                logger.info("Exiting borrow book menu.")
                return
            }
            else -> {
                println("Invalid input. Please enter a number from 1 to 5.")
                logger.warning("Invalid input provided in borrow book menu.")
            }
        }
    }
}

fun searchBook() {
    logger.info("Entering search book function.")

    println("")
    println("=".repeat(47))
    Thread.sleep(2000)

    println("\n[SEARCH A BOOK]")
    println("Please select how you would like to search for a book")
    println("[1] Search by Title")
    println("[2] Search by Author")
    println("[3] Search by Genre")
    println("[4] Exit\n")
    print("Enter your choice: ")
    when (readLine()?.toIntOrNull()) {
        1 -> {
            logger.info("Searching book by title.")
            println("\nSearch by Title:")
            print("Enter the title of the book: ")
            val title = readLine() ?: ""
            val matchingBooks = booksAvailable.filter { it.title.equals(title, ignoreCase = true) }
            if (matchingBooks.isEmpty()) {
                println("No books found with the title \"$title\".")
                logger.info("No books found with the title \"$title\".")
            } else {
                displayMatchingBooks(matchingBooks)
            }
        }
        2 -> {
            logger.info("Searching book by author.")
            println("\nSearch by Author:")
            print("Enter the author's name: ")
            val author = readLine() ?: ""
            val matchingBooks = booksAvailable.filter { it.author.equals(author, ignoreCase = true) }
            if (matchingBooks.isEmpty()) {
                println("No books found by the author \"$author\".")
                logger.info("No books found by the author \"$author\".")
            } else {
                displayMatchingBooks(matchingBooks)
            }
        }
        3 -> {
            logger.info("Searching book by genre.")
            println("\nSearch by Genre:")
            print("Enter the genre: ")
            val genre = readLine() ?: ""
            val matchingBooks = booksAvailable.filter { it.genre.equals(genre, ignoreCase = true) }
            if (matchingBooks.isEmpty()) {
                println("No books found in the genre \"$genre\".")
                logger.info("No books found in the genre \"$genre\".")
            } else {
                displayMatchingBooks(matchingBooks)
            }
        }
        4 -> {
            logger.info("Exiting search book function.")
            return
        }
        else -> {
            println("Invalid input. Please enter a number from 1 to 4.")
            logger.warning("Invalid input provided in search book function.")
        }
    }
}

fun displayMatchingBooks(matchingBooks: List<Book>) {
    logger.info("Displaying matching books.")

    if (matchingBooks.isEmpty()) {
        println("No matching books found.") // Displayed when no books match the search criteria.
        logger.info("No matching books found.")
        return
    }

    // Printing the header for the list of matching books.
    println("\nMatching Books:")
    println("%-4s| %-30s| %-20s| %-20s| %-15s".format(
            "No", "Book Title", "Author", "Genre", "Publication Date"
    ))
    println("-".repeat(100))

    // Printing details of each matching book.
    matchingBooks.forEachIndexed { index, book ->
        val title = book.title
        val author = book.author
        val genre = book.genre
        val pubDate = book.publicationDate

        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(index + 1, title, author, genre, pubDate))
    }
}

// This function facilitates the borrowing process for a book.
fun borrowBook() {
    logInfo("Entering borrowBook function.")

    println("")
    println("=".repeat(47))

    println("\n[BORROW A BOOK]\n")
    print("Please enter the title of the book you want to borrow: ")
    val title = readLine() ?: "" // Read the title of the book from the user input.
    val book = booksAvailable.find { it.title.equals(title, ignoreCase = true) } // Find the book in the available books list.

    if (book != null) { // If the book is found.
        println("\nHere is your book")
        val author = book.author
        val genre = book.genre
        val pubDate = book.publicationDate

        println("%-4s| %-30s| %-20s| %-20s| %-15s".format(
                "No", "Book Title", "Author", "Genre", "Publication Date"
        ))
        println("-".repeat(100))
        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(1, title, author, genre, pubDate))

        println("\nDo you want to borrow this book?\n") // Confirming if the user wants to borrow the book.
        println("[1] Yes")
        println("[2] No")
        print("\nEnter your choice: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("\nPlease enter your information:")
                print("First Name: ")
                val firstName = readLine() ?: ""
                print("Middle Name: ")
                val middleName = readLine() ?: ""
                print("Last Name: ")
                val lastName = readLine() ?: ""
                print("Course (BSIT, BSHM, BSBA, BEED): ")
                val course = readLine()?.uppercase() ?: ""
                print("School ID: ")
                val schoolId = readLine() ?: ""


                if (course !in listOf("BSIT", "BSHM", "BSBA", "BEED")) {
                    println("Invalid course.")
                    logInfo("Invalid course entered: $course")
                    return
                }
                print("Number of days to borrow: ")
                val daysToBorrow = readLine()?.toIntOrNull() ?: 0

                val borrowDate = LocalDate.now()
                val returnDate = borrowDate.plusDays(daysToBorrow.toLong())
                val borrower = Borrower(firstName, middleName, lastName, course, schoolId , borrowDate, returnDate)

                val borrowedBook = Book(book.title, book.author, book.genre, book.publicationDate, borrower, returnDate)

                println("\nSelected Book\n")
                println("%-4s| %-30s| %-20s| %-20s| %-20s| %-20s|%-20s| %-20s| %-20s".format(
                        "No", "Book Title", "Author", "Genre", "Publication Date", "Borrower", "Course", "School ID", "Return Date"
                ))
                println("-".repeat(158))

                val borrowerInfo = if (borrowedBook.borrower != null) {
                    "${borrowedBook.borrower?.firstName} ${borrowedBook.borrower?.middleName?.take(1)}. ${borrowedBook.borrower?.lastName}"
                } else {
                    "Unknown"
                }
                val schoolID = borrowedBook.borrower?.schoolID ?: "Unknown"
                val returnDateStr = borrowedBook.returnDate ?: "Unknown"

                println("%-4d| %-30s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s".format(
                        1, borrowedBook.title, borrowedBook.author, borrowedBook.genre, borrowedBook.publicationDate, borrowerInfo, course, schoolID, returnDateStr
                ))

                println("\nIs the Information Correct?\n") // Confirming again if the user wants to borrow the book.
                println("[1] Yes")
                println("[2] No")
                print("\nEnter your choice: ")

                when (readLine()?.toIntOrNull()) {
                    1 -> { // If the user confirms to borrow the book.
                        booksAvailable.remove(book) // Remove the book from available books list.
                        booksBorrowed.add(borrowedBook) // Add the book to borrowed books list.
                        println("Book borrowed successfully.")
                    }
                    2 -> println("Borrowing canceled.") // If the user cancels borrowing.
                    else -> println("Invalid input. Borrowing canceled.") // If the user provides invalid input.
                }
            }
            2 -> println("Borrowing canceled.") // If the user chooses not to borrow the book.
            else -> println("Invalid input. Borrowing canceled.") // If the user provides invalid input.
        }
    } else {
        println("\nSorry The Book $title is not found in the Library") // If the book is not found in the library.
        logError("Book not found in the library: $title")
    }

    logInfo("Exiting borrowBook function.")
}

private val logger: Logger = Logger.getLogger("LibrarySystem")

fun logInfo(message: String) {
    logger.info(message)
}

fun logError(message: String, throwable: Throwable? = null) {
    if (throwable != null) {
        logger.severe("$message: ${throwable.message}")
    } else {
        logger.severe(message)
    }
}
