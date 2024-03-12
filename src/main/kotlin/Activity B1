import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Book(val title: String, val author: String, val genre: String, val publicationDate: String)

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
            3 -> listBooks(booksAvailable, "available")
            4 -> listBooks(booksBorrowed, "borrowed")
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
    var publicationDate: String
    do {
        print("Publication Date (yyyy-MM-dd): ")
        publicationDate = readLine() ?: ""
        if (!publicationDate.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
            println("Error: Please enter the publication date in the format yyyy-MM-dd.")
        }
    } while (!publicationDate.matches(Regex("\\d{4}-\\d{2}-\\d{2}")))

    val newBook = Book(title, author, genre, publicationDate)
    val existingBook = booksAvailable.find { it.title == newBook.title }
    if (existingBook != null) {
        println("Error: The book already exists in the library.")
    } else {
        println("\nAre you sure you want to add this Book?")
        displayBookInformation(newBook)
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
        displayBookInformation(book)
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

fun listBooks(books: List<Book>, type: String) {
    println("\nHere are the books $type in the library:\n")
    if (books.isEmpty()) {
        println("No books $type in the library.")
    } else {
        books.forEachIndexed { index, book ->
            println("${index + 1}. ${book.title} by ${book.author}")
        }
    }
}

fun updateBook() {
    println("\nUpdate Book Information:")
    print("Enter the title of the book you want to update: ")
    val title = readLine() ?: ""
    val book = booksAvailable.find { it.title.equals(title, ignoreCase = true) }
    if (book != null) {
        println("\nSelected Book:")
        displayBookInformation(book)
        println("\nPlease provide the updated information for the book:")
        print("New Title (Press Enter to keep existing title): ")
        val newTitle = readLine()?.takeIf { it.isNotBlank() } ?: book.title
        print("New Author (Press Enter to keep existing author): ")
        val newAuthor = readLine()?.takeIf { it.isNotBlank() } ?: book.author
        print("New Genre (Press Enter to keep existing genre): ")
        val newGenre = readLine()?.takeIf { it.isNotBlank() } ?: book.genre
        var newPublicationDate = book.publicationDate
        var validDate = false
        do {
            print("New Publication Date (yyyy-MM-dd, Press Enter to keep existing date): ")
            newPublicationDate = readLine() ?: book.publicationDate
            if (newPublicationDate.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
                validDate = true
            } else {
                println("Error: Please enter the publication date in the format yyyy-MM-dd.")
            }
        } while (!validDate)

        val updatedBook = Book(newTitle, newAuthor, newGenre, newPublicationDate)
        booksAvailable.remove(book)
        booksAvailable.add(updatedBook)
        println("\nBook information updated successfully:")
        displayBookInformation(updatedBook)
    } else {
        println("Book not found.")
    }
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
            2 -> listBooks(booksAvailable, "available")
            3 -> listBooks(booksBorrowed, "borrowed")
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
    println("\nMatching Books:")
    matchingBooks.forEachIndexed { index, book ->
        println("${index + 1}. ${book.title} by ${book.author}")
    }
    print("\nSelect the book to borrow (Enter the corresponding number): ")
    val choice = readLine()?.toIntOrNull() ?: return
    if (choice in 1..matchingBooks.size) {
        val selectedBook = matchingBooks[choice - 1]
        println("Book Information")
        displayBookInformation(selectedBook)
        borrowConfirmation(selectedBook)
    } else {
        println("Invalid choice.")
    }
}


fun borrowBook() {
    println("\nBorrow Book:")
    print("Please enter the title of the book you want to borrow: ")
    val title = readLine() ?: ""
    val book = booksAvailable.find { it.title.equals(title, ignoreCase = true) }
    if (book != null) {
        println("\nMatching Books:")
        displayBookInformation(book)
        println("\nDo you want to borrow this book?")
        println("[1] Yes")
        println("[2] No")
        print("Enter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> borrowConfirmation(book)
            2 -> println("Borrowing canceled.")
            else -> println("Invalid input. Borrowing canceled.")
        }
    } else {
        println("Book not found.")
    }
}

fun borrowConfirmation(book: Book) {
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

            val borrowedBook = Book(book.title, book.author, book.genre, book.publicationDate)
            booksAvailable.remove(book)
            booksBorrowed.add(borrowedBook)
            val returnDate = LocalDate.now().plusDays(daysToBorrow.toLong())
            println("\nBorrower Information:")
            println("Name: $firstName $middleName $lastName")
            println("School ID: $schoolId")
            println("\nSelected Book:")
            displayBookInformation(borrowedBook)
            println("Date to Return: $returnDate")
            println("\nBorrowing successful! You have borrowed ${book.title} by ${book.author} for $daysToBorrow days.")
        }
        2 -> println("Borrowing canceled.")
        else -> println("Invalid input. Borrowing canceled.")
    }
}

fun displayBookInformation(book: Book) {
    with(book) {
        println("Title: $title")
        println("Author: $author")
        println("Genre: $genre")
        println("Publication Date: $publicationDate")
    }
}
