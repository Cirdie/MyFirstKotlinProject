data class Product(
    var name: String = "",
    var price: Int = 0,
    var category: String = "",
    var condition: String = "",
    var description: String = ""
)

fun main() {
    println("===== WELCOME TO HOME AND GARDEN PRODUCT INVENTORY SYSTEM =====")

    var userName: String
    var numProductsToAdd: Int

    do {
        print("\nPlease Enter your Name: ")
        userName = readLine() ?: ""

        print("Please Indicate how many Products you are going to Add: ")
        numProductsToAdd = readLine()?.toIntOrNull() ?: 0
    } while (numProductsToAdd <= 0)

    val inventory = mutableListOf<Product>()

    repeat(numProductsToAdd) {
        println("\nAdding Product ${it + 1}")
        val product = addProduct()
        inventory.add(product)
        println("\n===== Product Added Successfully =====")
    }

    println("\nThere are ${inventory.size} product(s) in Inventory")
    displayProducts(inventory)

    var addMoreProducts: String
    do {
        print("\nAdd More Product? [Y/N]: ")
        addMoreProducts = readLine()?.toUpperCase() ?: ""
        if (addMoreProducts == "Y") {
            print("\nPlease Indicate how many More Products you are going to Add: ")
            val numMoreProducts = readLine()?.toIntOrNull() ?: 0
            repeat(numMoreProducts) {
                println("\nAdding Product ${inventory.size + it + 1}")
                val product = addProduct()
                inventory.add(product)
                println("\n===== Product Added Successfully =====")
            }
            println("\nThere are ${inventory.size} product(s) in Inventory")
            displayProducts(inventory)
        }
    } while (addMoreProducts == "Y")

    println("\nThank you for using the Inventory System")
}

fun addProduct(): Product {
    println("==================================================")
    print("Please Enter Product Name: ")
    val name = readLine() ?: ""

    var price: Int
    do {
        print("Price of the Product: ")
        price = readLine()?.toIntOrNull() ?: 0
    } while (price <= 0)

    println("==================================================")
    println("Category of the Product:")
    println("[1] Tools\n[2] Furniture\n[3] Household\n[4] Garden\n[5] Appliances\n[6] Others")
    var category: String
    do {
        print("Enter the category number: ")
        category = when (readLine()?.toIntOrNull() ?: 0) {
            1 -> "Tools"
            2 -> "Furniture"
            3 -> "Household"
            4 -> "Garden"
            5 -> "Appliances"
            6 -> "Others"
            else -> ""
        }
    } while (category.isEmpty())

    println("==================================================")
    println("Condition of the product:")
    println("[1] New\n[2] Used\n[3] Damage")
    var condition: String
    do {
        print("Enter the condition number: ")
        condition = when (readLine()?.toIntOrNull() ?: 0) {
            1 -> "New"
            2 -> "Used"
            3 -> "Damage"
            else -> ""
        }
    } while (condition.isEmpty())

    print("Add Description of the Product: ")
    val description = readLine() ?: ""

    println("\nProduct Details:")
    println("Name: $name")
    println("Price: $price")
    println("Category: $category")
    println("Condition: $condition")
    println("Description: $description")

    println("==================================================")
    var confirm: String
    do {
        print("Are you sure you want to add this product? [Y/N]: ")
        confirm = readLine()?.toUpperCase() ?: ""
    } while (confirm != "Y" && confirm != "N")

    return if (confirm == "Y") {
        Product(name, price, category, condition, description)
    } else {
        addProduct()
    }
}

fun displayProducts(products: List<Product>) {
    println("==================================================")
    products.forEachIndexed { index, product ->
        println("Product Number ${index + 1}:")
        println("Product: ${product.name}")
        println("Price: ${product.price}")
        println("Category: ${product.category}")
        println("Condition: ${product.condition}")
        println("Description: ${product.description}")
        println("==================================================")
    }
}
