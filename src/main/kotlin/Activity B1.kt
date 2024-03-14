import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

data class Book(
        val title: String,
        val author: String,
        val genre: String,
        val publicationDate: LocalDate,
        var borrower: Borrower? = null,
        var returnDate: LocalDate? = null // Corrected returnDate property type
)

data class Borrower(
        val firstName: String,
        val middleName: String,
        val lastName: String,
        val schoolID: String,
        val borrowDate: LocalDate,
        val returnDate: LocalDate
)



val booksAvailable = mutableListOf<Book>()
val booksBorrowed = mutableListOf<Book>()

fun main() {
    println("WELCOME TO LIBRARY SYSTEM")
    while (true) {
        println("\n[1] Add a book")
        println("[2] Borrow a book")
        println("[3] Exit\n")
        print("Enter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> addBookMenu()
            2 -> borrowBookMenu()
            3 -> {
                println("Exiting the Library System. Goodbye!")
                return
            }
            else -> println("Invalid input. Please enter a number from 1 to 3.")
        }
    }
}

fun addBookMenu() {
    println("\n//ADDING BOOK MODULE//")
    while (true) {
        println("\n[1] Add Book to Library")
        println("[2] Remove Book from Library")
        println("[3] List Books Available")
        println("[4] List Books Borrowed")
        println("[5] Update Book Information")
        println("[6] Exit Add Book Menu\n")
        print("Enter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> addBook()
            2 -> removeBook()
            3 -> listAvailableBooks()
            4 -> listBorrowedBooks()
            5 -> updateBook()
            6 -> return
            else -> println("Invalid input. Please enter a number from 1 to 6.")
        }
    }
}
fun addBook() {
    println("\nPlease provide the following information for adding a new book:")
    print("Title: ")
    val title = readLine() ?: ""
    print("Author: ")
    val author = readLine() ?: ""
    print("Genre: ")
    val genre = readLine() ?: ""

    val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
    var publicationDate: LocalDate? = null

    do {
        print("Publication Date (MM-dd-yyyy): ")
        val publicationDateStr = readLine() ?: ""
        try {
            publicationDate = LocalDate.parse(publicationDateStr, formatter)

        } catch (e: DateTimeParseException) {
            println("Error: Please enter the publication date in the format MM-dd-yyyy.")
        }
    } while (publicationDate == null)

    val newBook = Book(title, author, genre, publicationDate)
    val existingBook = booksAvailable.find { it.title == newBook.title }
    if (existingBook != null) {
        println("Error: The book already exists in the library.")
    } else {
        println("\nAre you sure you want to add this Book?")
        println("%-4s| %-30s| %-20s| %-20s| %-15s".format("No", "Book Title", "Author", "Genre", "Publication Date"))
        println("-".repeat(100))

        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(1, title, author, genre, publicationDate))

        print("\n[1] Yes\n[2] No\n\nEnter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                booksAvailable.add(newBook)
                println("Book added successfully.")
            }
            2 -> println("Addition canceled.")
            else -> println("Invalid input. Addition canceled.")
        }
    }
}



fun removeBook() {
    println("\nRemove Book from Library:")
    if (booksAvailable.isEmpty()) {
        println("No books available in the library to remove.")
        return
    }
    print("Enter Book title: ")
    val title = readLine() ?: ""
    val book = booksAvailable.find { it.title.equals(title, ignoreCase = true) }
    if (book != null) {
        println("\nAre you sure you want to delete this Book?")
        print("\n[1] Yes\n[2] No\n\nEnter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                booksAvailable.remove(book)
                println("Book removed successfully.")
            }
            2 -> println("Removal canceled.")
            else -> println("Invalid input. Removal canceled.")
        }
    } else {
        println("Book not found.")
    }
}

fun listAvailableBooks() {
    if (booksAvailable.isEmpty()) {
        println("No books available in the library.")
        return
    }

    println("\nAvailable Books:")
    println("%-4s| %-30s| %-20s| %-20s| %-15s".format("No", "Book Title", "Author", "Genre", "Publication Date"))
    println("-".repeat(100))

    booksAvailable.forEachIndexed { index, book ->
        val title = book.title
        val author = book.author
        val genre = book.genre
        val pubDate = book.publicationDate

        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(index + 1, title, author, genre, pubDate))
    }
}fun listBorrowedBooks() {
    if (booksBorrowed.isEmpty()) {
        println("No books borrowed from the library.")
        return
    }

    println("\nBorrowed Books:")
    println("%-4s| %-30s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s".format(
            "No", "Book Title", "Author", "Genre", "Publication Date", "Borrower", "School ID", "Return Date"
    ))
    println("-".repeat(150))

    booksBorrowed.forEachIndexed { index, book ->
        val title = book.title
        val author = book.author
        val genre = book.genre
        val pubDate = book.publicationDate
        val borrowerInfo = if (book.borrower != null) {
            val borrower = book.borrower
            "${borrower?.firstName} ${borrower?.middleName?.take(1)}. ${borrower?.lastName}"
        } else {
            "Unknown"
        }
        val schoolID = book.borrower?.schoolID ?: "Unknown"
        val returnDate = book.returnDate ?: "Unknown"

        println("%-4d| %-30s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s".format(
                index + 1, title, author, genre, pubDate, borrowerInfo, schoolID,returnDate
        ))
    }
}





fun updateBook() {
    println("\nPlease provide the following information to update a book:")
    print("Enter the title of the book you want to update: ")
    val titleToUpdate = readLine() ?: ""

    val bookToUpdate = booksAvailable.find { it.title.equals(titleToUpdate, ignoreCase = true) }
    if (bookToUpdate == null) {
        println("Error: The book with the title '$titleToUpdate' does not exist in the library.")
        return
    }

    println("\nCurrent Book Information:")
    println("%-4s| %-30s| %-20s| %-20s| %-15s".format("No", "Book Title", "Author", "Genre", "Publication Date"))
    println("-".repeat(100))

    booksAvailable.forEachIndexed { index, book ->
        val title = book.title
        val author = book.author
        val genre = book.genre
        val pubDate = book.publicationDate

        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(index + 1, title, author, genre, pubDate))
    }
    println("\nPlease provide the updated information for the book:")
    print("New Title (Press Enter to keep existing title): ")
    val newTitle = readLine()?.takeUnless { it.isBlank() } ?: bookToUpdate.title
    print("New Author (Press Enter to keep existing author): ")
    val newAuthor = readLine()?.takeUnless { it.isBlank() } ?: bookToUpdate.author
    print("New Genre (Press Enter to keep existing genre): ")
    val newGenre = readLine()?.takeUnless { it.isBlank() } ?: bookToUpdate.genre

    val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
    var newPublicationDate: LocalDate? = null

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

    val updatedBook = Book(newTitle, newAuthor, newGenre, newPublicationDate ?: bookToUpdate.publicationDate)
    booksAvailable.remove(bookToUpdate)
    booksAvailable.add(updatedBook)

    println("Book updated successfully.")
}

fun borrowBookMenu() {
    println("\nFor borrowing a Book Menu")
    while (true) {
        println("\n//BORROWING MODULE//")
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
            5 -> return
            else -> println("Invalid input. Please enter a number from 1 to 5.")
        }
    }
}

fun searchBook() {
    println("\nBorrowing Menu - Search Book:")
    println("Please select how you would like to search for a book:")
    println("[1] Search by Title")
    println("[2] Search by Author")
    println("[3] Search by Genre")
    println("[4] Exit\n")
    print("Enter your choice: ")
    when (readLine()?.toIntOrNull()) {
        1 -> {
            println("\nSearch by Title:")
            print("Enter the title of the book: ")
            val title = readLine() ?: ""
            val matchingBooks = booksAvailable.filter { it.title.equals(title, ignoreCase = true) }
            if (matchingBooks.isEmpty()) {
                println("No books found with the title \"$title\".")
            } else {
                displayMatchingBooks(matchingBooks)
            }
        }
        2 -> {
            println("\nSearch by Author:")
            print("Enter the author's name: ")
            val author = readLine() ?: ""
            val matchingBooks = booksAvailable.filter { it.author.equals(author, ignoreCase = true) }
            if (matchingBooks.isEmpty()) {
                println("No books found by the author \"$author\".")
            } else {
                displayMatchingBooks(matchingBooks)
            }
        }
        3 -> {
            println("\nSearch by Genre:")
            print("Enter the genre: ")
            val genre = readLine() ?: ""
            val matchingBooks = booksAvailable.filter { it.genre.equals(genre, ignoreCase = true) }
            if (matchingBooks.isEmpty()) {
                println("No books found in the genre \"$genre\".")
            } else {
                displayMatchingBooks(matchingBooks)
            }
        }
        4 -> return
        else -> println("Invalid input. Please enter a number from 1 to 4.")
    }
}
fun displayMatchingBooks(matchingBooks: List<Book>) {
    if (matchingBooks.isEmpty()) {
        println("No matching books found.")
        return
    }

    println("\nMatching Books:")
    println("%-4s| %-30s| %-20s| %-20s| %-15s".format(
            "No", "Book Title", "Author", "Genre", "Publication Date"
    ))
    println("-".repeat(100))

    matchingBooks.forEachIndexed { index, book ->
        val title = book.title
        val author = book.author
        val genre = book.genre
        val pubDate = book.publicationDate

        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(index + 1, title, author, genre, pubDate))
    }
}

fun borrowBook() {
    println("\nBorrow Book:")
    print("Please enter the title of the book you want to borrow: ")
    val title = readLine() ?: ""
    val book = booksAvailable.find { it.title.equals(title, ignoreCase = true) }
    if (book != null) {
        println("\nMatching Books:")
        val author = book.author
        val genre = book.genre
        val pubDate = book.publicationDate

        println("%-4s| %-30s| %-20s| %-20s| %-15s".format(
                "No", "Book Title", "Author", "Genre", "Publication Date"
        ))
        println("-".repeat(100))
        println("%-4d| %-30s| %-20s| %-20s| %-15s".format(1, title, author, genre, pubDate))

        println("\nDo you want to borrow this book?")
        println("[1] Yes")
        println("[2] No")
        print("Enter your choice: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("\nPlease enter your information:")
                print("First Name: ")
                val firstName = readLine() ?: ""
                print("Middle Name: ")
                val middleName = readLine() ?: ""
                print("Last Name: ")
                val lastName = readLine() ?: ""
                print("School ID: ")
                val schoolId = readLine() ?: ""
                print("Number of days to borrow: ")
                val daysToBorrow = readLine()?.toIntOrNull() ?: 0

                val borrowDate = LocalDate.now()
                val returnDate = borrowDate.plusDays(daysToBorrow.toLong())
                val borrower = Borrower(firstName, middleName, lastName, schoolId, borrowDate, returnDate)

                val borrowedBook = Book(book.title, book.author, book.genre, book.publicationDate, borrower, returnDate)

                println("\nBorrower Information:")
                println("Name: $firstName $middleName $lastName")
                println("School ID: $schoolId")
                println("\nSelected Book:")
                println("%-4s| %-30s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s".format(
                        "No", "Book Title", "Author", "Genre", "Publication Date", "Borrower", "School ID", "Return Date"
                ))
                println("-".repeat(150))

                val borrowerInfo = if (borrowedBook.borrower != null) {
                    "${borrowedBook.borrower?.firstName} ${borrowedBook.borrower?.middleName?.take(1)}. ${borrowedBook.borrower?.lastName}"
                } else {
                    "Unknown"
                }
                val schoolID = borrowedBook.borrower?.schoolID ?: "Unknown"
                val returnDateStr = borrowedBook.returnDate ?: "Unknown"

                println("%-4d| %-30s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s".format(
                        1, borrowedBook.title, borrowedBook.author, borrowedBook.genre, borrowedBook.publicationDate, borrowerInfo, schoolID, returnDateStr
                ))

                println("\nDo you want to borrow this book?")
                println("[1] Yes")
                println("[2] No")
                print("Enter your choice: ")

                when (readLine()?.toIntOrNull()) {
                    1 -> {
                        booksAvailable.remove(book)
                        booksBorrowed.add(borrowedBook)
                        println("Book borrowed successfully.")
                    }
                    2 -> println("Borrowing canceled.")
                    else -> println("Invalid input. Borrowing canceled.")
                }
            }
            2 -> println("Borrowing canceled.")
            else -> println("Invalid input. Borrowing canceled.")
        }
    } else {
        println("Book not found.")
    }
}
