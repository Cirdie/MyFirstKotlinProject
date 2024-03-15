import kotlin.random.Random
import java.text.DecimalFormat

data class Product(
        val code: Int,
        var name: String,
        var category: String,
        var price: Double,
        var quantity: Int,
        var salesCount: Int = 0,
        var totalCost: Double = 0.0
)

val inventory = mutableListOf<Product>()



fun main() {
    println("=".repeat(10) + " GROCERY MANAGEMENT SYSTEM " + "=".repeat(10))

    while (true) {
        println("\nSelect a number to choose an option")
        println("[1] Add Product to Inventory")
        println("[2] Update Product Sales")
        println("[3] Update Product Information")
        println("[4] View Inventory")
        println("[5] Search Product")
        println("[6] View Sales Report")
        println("[7] Exit\n")
        print("Please enter a number your choice: ")

        when (readLine()?.toIntOrNull()) {
            1 -> addProductToInventory()
            2 -> updateProductSales()
            3 -> updateProductInformation()
            4 -> viewInventory()
            5 -> searchProduct()
            6 -> generateSalesReport()
            7 -> {
                println("Exiting the Grocery Management System. Goodbye!")
                return
            }
            else -> println("Invalid input. Please enter a number from 1 to 7.")
        }
    }
}

fun addProductToInventory() {
    println("=".repeat(10) + " ADD PRODUCT TO INVENTORY " + "=".repeat(10))
    print("\nProduct Name: ")
    val name = readLine() ?: ""

    if (inventory.any { it.name.equals(name, ignoreCase = true) }) {
        println("A product with the same name already exists. Please enter a unique name.")
        return
    }

    var category = "" // Initialize category variable here
    var validCategory = false
    while (!validCategory) {
        println("\nProduct Category")
        println("[1] Accessories & Enhancement")
        println("[2] Home & Lifestyle")
        println("[3] Family & Lifestyle")
        println("[4] Groceries")
        print("Your Choice: ")
        category = when (readLine()?.toIntOrNull()) {
            1 -> "Accessories & Enhancement"
            2 -> "Home & Lifestyle"
            3 -> "Family & Lifestyle"
            4 -> "Groceries"
            else -> {
                println("Invalid category. Please choose a valid category.")
                continue
            }
        }
        validCategory = true
    }

    print("\nProduct Price: ")
    val priceString = readLine() ?: ""
    val price = try {
        DecimalFormat("#,##0.00").parse(priceString).toDouble()
    } catch (e: Exception) {
        println("Invalid price format. Please enter a valid price.")
        return
    }

    print("Product Quantity: ")
    val quantity = readLine()?.toIntOrNull() ?: 0

    val code = Random.nextInt(10000, 99999) // Generating a random 5-digit code
    val newProduct = Product(code, name, category, price, quantity)
    inventory.add(newProduct)

    println("\nProduct added to inventory successfully.\n")
    println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
            "Code", "Product Name", "Categories", "Price", "Qty", "Sales Count", "Total Cost"
    ))
    println("-".repeat(130))
    println("%-8s| %-30s| %-30s| %-15s| %-5d| %-12d| %-15.2f".format(
            newProduct.code, newProduct.name, newProduct.category, "₱${"%.2f".format(newProduct.price)}",
            newProduct.quantity, newProduct.salesCount, newProduct.totalCost
    ))
}
fun updateProductSales() {
    println("=".repeat(10) + " UPDATE PRODUCT SALES " + "=".repeat(10))
    print("\nPlease enter the product name or code: ")
    val input = readLine() ?: ""
    val product = inventory.find { it.name.equals(input, ignoreCase = true) || it.code.toString() == input }
    if (product == null) {
        println("Product not found.")
        return
    }

    // Display current product information
    println("\nProduct Information:")
    println("%-8s| %-30s| %-30s| %-15s| %-8s| %-12s| %-15s".format(
            "Code", "Product Name", "Categories", "Price", "Quantity", "Sales Count", "Total Cost"
    ))
    println("-".repeat(130))
    println("%-8s| %-30s| %-30s| %-15s| %-8s| %-12s| ₱%-15.2f".format(
            product.code, product.name, product.category, "₱${"%.2f".format(product.price)}",
            product.quantity, product.salesCount, product.totalCost
    ))

    // Enter new sales count
    println("\nEnter the new sales count for the product:")
    val newSalesCount = readLine()?.toIntOrNull() ?: return

    // Check if new sales count exceeds available quantity
    if (newSalesCount > product.quantity) {
        println("Sales count cannot exceed available quantity (${product.quantity}).")
        return
    }

    // Update sales count and subtract from quantity
    val quantityDifference = newSalesCount - product.salesCount
    product.salesCount = newSalesCount
    product.quantity -= quantityDifference

    // Calculate new total cost
    product.totalCost = newSalesCount * product.price

    // Display success message and updated product information
    println("\n[Success] The sales count for ${product.name} has been updated to $newSalesCount.")
    println("New Total Cost for ${product.name}: ₱${"%.2f".format(product.totalCost)}")
    println("New Quantity for ${product.name}: ${product.quantity}")

    // Display updated product information
    println("\nUpdated Product Information:")
    println("%-8s| %-30s| %-30s| %-15s| %-8s| %-12s| %-15s".format(
            "Code", "Product Name", "Categories", "Price", "Qty", "Sales Count", "Total Cost"
    ))
    println("-".repeat(130))
    println("%-8s| %-30s| %-30s| %-15s| %-8s| %-12s| ₱%-15.2f".format(
            product.code, product.name, product.category, "₱${"%.2f".format(product.price)}",
            product.quantity, product.salesCount, product.totalCost
    ))
}


fun updateProductInformation() {
    println("=".repeat(10) + " UPDATE PRODUCT INFORMATION " + "=".repeat(10))
    print("\nPlease enter the product name or code: ")
    val input = readLine() ?: ""


    val product = inventory.find { it.name.equals(input, ignoreCase = true) || it.code.toString() == input }
    if (product == null) {
        println("Product not found.")
        return
    }

    while (true) {
        print("\nEnter the new product name: ")
        val newName = readLine()?.trim() ?: ""
        if (newName.isBlank()) {
            println("Product name cannot be empty.")
            continue
        }

        if (inventory.any { it.name.equals(newName, ignoreCase = true) }) {
            println("A product with the same name already exists. Please enter a unique name.")
            continue
        }

        // If the new name is unique, update the product name and break the loop
        product.name = newName
        break
    }

    println("\nProduct Information:")
    println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
            "Code", "Product Name", "Categories", "Price", "Qty", "Sales Count", "Total Cost"
    ))
    println("-".repeat(130))
    println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
            product.code, product.name, product.category, "₱${"%.2f".format(product.price)}",
            product.quantity, product.salesCount, "₱${"%.2f".format(product.totalCost)}"
    ))

    println("\nSelect the information you want to update:")
    println("[1] Product Name")
    println("[2] Product Price")
    println("[3] Product Quantity")
    println("[4] Product Category")
    print("> ")
    val choice = readLine()?.toIntOrNull() ?: return

    when (choice) {
        1 -> {
            print("\nEnter the new product name: ")
            val newName = readLine()?.trim() ?: ""
            if (newName.isBlank()) {
                println("Product name cannot be empty.")
            }
            if (inventory.any { it.name.equals(newName, ignoreCase = true) }) {
                println("A product with the same name already exists. Please enter a unique name.")
            }
            product.name = newName

            println("\nAre you sure you want to update the product name to '$newName'?")
        }
        2 -> {
            print("Enter the new product price: ")
            val newPrice = readLine()?.toDoubleOrNull() ?: return
            if (newPrice < 0.0) {
                println("Product Price cannot be empty.")
            }
            product.price = newPrice
            println("\nAre you sure you want to update the product price to '₱${"%.2f".format(newPrice)}'?")
        }
        3 -> {
            print("Enter the new product quantity: ")
            val newQuantity = readLine()?.toIntOrNull() ?: return
            if (newQuantity < 0) {
                println("Product Quantity cannot be empty.")
            }
            product.quantity = newQuantity
            println("\nAre you sure you want to update the product quantity to '$newQuantity'?")
        }
        4 -> {
            // Update product category
            print("Enter the new product category: ")
            val newCategory = readLine() ?: return
            var validCategory = false
            while (!validCategory) {
                println("\nProduct Category")
                println("[1] Accessories & Enhancement")
                println("[2] Home & Lifestyle")
                println("[3] Family & Lifestyle")
                println("[4] Groceries")
                print("Your Choice: ")
                product.category = when (readLine()?.toIntOrNull()) {
                    1 -> "Accessories & Enhancement"
                    2 -> "Home & Lifestyle"
                    3 -> "Family & Lifestyle"
                    4 -> "Groceries"
                    else -> {
                        println("Invalid Number. Please choose a valid number.")
                        continue
                    }
                }
                validCategory = true
            }
            println("\nAre you sure you want to update the product category to '$newCategory'?\n")
        }
        else -> {
            println("Invalid choice.")
            return
        }
    }

    println("[1] Yes")
    println("[2] No")
    print("\nEnter Your Choice\n")
    val confirmChoice = readLine()?.toIntOrNull() ?: return

    when (confirmChoice) {
        1 -> {
            println("[Success] The selected information for '${product.name}' has been updated.")
            println("\nUpdated Product Information:")
            println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
                    "Code", "Product Name", "Categories", "Price", "Qty", "Sales Count", "Total Cost"
            ))
            println("-".repeat(130))
            println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
                    product.code, product.name, product.category, "₱${"%.2f".format(product.price)}",
                    product.quantity, product.salesCount, "₱${"%.2f".format(product.totalCost)}"
            ))
        }
        2 -> println("Update canceled.")
        else -> println("Invalid choice. Update canceled.")
    }
}

fun viewInventory() {
    println("\nInventory List:\n")
    println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
            "Code", "Product Name", "Categories", "Price", "Qty", "Sales Count", "Total Cost"
    ))
    println("-".repeat(130))

    inventory.forEachIndexed { index, product ->
        println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
                product.code, product.name, product.category, "₱${"%.2f".format(product.price)}",
                product.quantity, product.salesCount, "₱${"%.2f".format(product.totalCost)}"
        ))
    }
}
fun searchProduct() {
    println("\n[SEARCH A PRODUCT]")
    println("Please select how you would like to search for a product")
    println("[1] Search by Name")
    println("[2] Search by Category")
    println("[3] Search by Code")
    println("[4] Exit\n")
    print("Enter your choice: ")
    when (readLine()?.toIntOrNull()) {
        1 -> {
            println("\n[Search by Name]\n")
            print("Enter the name of the product: ")
            val productName = readLine() ?: ""
            val matchingProducts = inventory.filter { it.name.equals(productName, ignoreCase = true) }
            displayMatchingProducts(matchingProducts)
        }
        2 -> {
            println("\n[Search by Category]\n")
            print("Enter the category: ")
            val productCategory = readLine() ?: ""
            val matchingProducts = inventory.filter { it.category.equals(productCategory, ignoreCase = true) }
            displayMatchingProducts(matchingProducts)
        }
        3 -> {
            println("\n[Search by Code]\n")
            print("Enter the code of the product: ")
            val productCode = readLine()?.toIntOrNull() ?: 0
            val matchingProduct = inventory.find { it.code == productCode }
            if (matchingProduct == null) {
                println("No product found with code \"$productCode\".")
            } else {
                displayMatchingProducts(listOf(matchingProduct))
            }
        }
        4 -> {
            println("Exiting search product function.")
            return
        }
        else -> {
            println("Invalid input. Please enter a number from 1 to 4.")
        }
    }
}

fun displayMatchingProducts(products: List<Product>) {
    println("\nMatching Products:")
    println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
            "Code", "Product Name", "Categories", "Price", "Qty", "Sales Count", "Total Cost"
    ))
    println("-".repeat(130))
    products.forEach { product ->
        println("%-8s| %-30s| %-30s| %-15s| %-5s| %-12s| %-15s".format(
                product.code, product.name, product.category, "₱${"%.2f".format(product.price)}",
                product.quantity, product.salesCount, "₱${"%.2f".format(product.totalCost)}"
        ))
    }
}

fun generateSalesReport() {
    println("\nGenerating sales report...\n")
    println("Sales Report:")
    println("-".repeat(95))
    println("%-13s| %-20s| %-14s| %-12s| %-15s".format(
            "Product Code", "Product Name", "Price", "Quantity Sold", "Total Sales"))
    println("-".repeat(95))

    var totalSales = 0.0

    inventory.forEach { product ->
        val productSales = product.salesCount
        val totalSalesForProduct = productSales * product.price
        totalSales += totalSalesForProduct
        println("%-8s| %-30s| %-15.2f| %-15d| ₱%-15.2f".format(
                product.code, "${product.name} (₱${"%.2f".format(product.price)})",
                product.price, productSales, totalSalesForProduct))
    }

    println("-".repeat(95))
    println("Total Sales: ₱${"%.2f".format(totalSales)}")
}