fun main() {
    var userName: String

    println("Please Enter your Name:")
    userName = readLine()!!

    var numProducts: Int
    var addMore: String

    println("Please Indicate how many Products you are going to Add:")
    numProducts = readLine()?.toIntOrNull() ?: 0

    for (i in 1..numProducts) {
        println("==================================================")
        println("Product Number $i")
        println("Please Enter Product Name:")
        val productName = readLine()!!

        var price: Double? = null
        while (price == null) {
            println("Price of the Product:")
            price = readLine()?.toDoubleOrNull()
            if (price == null) {
                println("Invalid input. Please enter a valid price.")
            }
        }

        var category = ""
        do {
            println("Category of the Product:")
            println("[1] Tools")
            println("[2] Furniture")
            println("[3] Household")
            println("[4] Garden")
            println("[5] Appliances")
            println("[6] Others")
            val categoryInput = readLine()?.toIntOrNull()
            category = when (categoryInput) {
                1 -> "Tools"
                2 -> "Furniture"
                3 -> "Household"
                4 -> "Garden"
                5 -> "Appliances"
                6 -> "Others"
                else -> {
                    println("Invalid category. Please enter a number between 1 and 6.")
                    continue
                }
            } ?: ""
        } while (category !in listOf("Tools", "Furniture", "Household", "Garden", "Appliances", "Others"))

        var condition = ""
        do {
            println("Condition of the product:")
            println("[1] New")
            println("[2] Used")
            println("[3] Damage")
            val conditionInput = readLine()?.toIntOrNull()
            condition = when (conditionInput) {
                1 -> "New"
                2 -> "Used"
                3 -> "Damage"
                else -> {
                    println("Invalid condition. Please enter a number between 1 and 3.")
                    continue
                }
            } ?: ""
        } while (condition !in listOf("New", "Used", "Damage"))

        println("Add Description of the Product:")
        val description = readLine()!!

        println("Are you sure you want to Enter this product?")
        println("Product: $productName")
        println("Price: Php $price")
        println("Category: $category")
        println("Condition: $condition")
        println("Description:")
        println("\"$description\"")
        println("[1] Yes")
        println("[2] No")

        val confirm = readLine()!!
        if (confirm.equals("1", ignoreCase = true)) {
            println("===== Product Added Successfully =====")
            println("Product Number $i:")
            println("Product: $productName")
            println("Price: Php $price")
            println("Category: $category")
            println("Condition: $condition")
            println("Description:")
            println("\"$description\"")
        } else {
            println("Product addition cancelled for Product Number $i")
        }
    }

    addMore = "Yes"
    while (addMore != "2") {
        println("Add More Product?")
        println("[1] Yes")
        println("[2] No")
        addMore = readLine()!!
        if (addMore == "1") {
            println("Please Indicate how many More Products you want to Add:")
            val moreProducts = readLine()?.toIntOrNull() ?: 0
            for (i in 1..moreProducts) {
                println("==================================================")
                println("Product Number ${numProducts + i}")
                println("Please Enter Product Name:")
                val productName = readLine()!!

                var price: Double? = null
                while (price == null) {
                    println("Price of the Product:")
                    price = readLine()?.toDoubleOrNull()
                    if (price == null) {
                        println("Invalid input. Please enter a valid price.")
                    }
                }

                var category = ""
                do {
                    println("Category of the Product:")
                    println("[1] Tools")
                    println("[2] Furniture")
                    println("[3] Household")
                    println("[4] Garden")
                    println("[5] Appliances")
                    println("[6] Others")
                    val categoryInput = readLine()?.toIntOrNull()
                    category = when (categoryInput) {
                        1 -> "Tools"
                        2 -> "Furniture"
                        3 -> "Household"
                        4 -> "Garden"
                        5 -> "Appliances"
                        6 -> "Others"
                        else -> {
                            println("Invalid category. Please enter a number between 1 and 6.")
                            continue
                        }
                    } ?: ""
                } while (category !in listOf("Tools", "Furniture", "Household", "Garden", "Appliances", "Others"))

                var condition = ""
                do {
                    println("Condition of the product:")
                    println("[1] New")
                    println("[2] Used")
                    println("[3] Damage")
                    val conditionInput = readLine()?.toIntOrNull()
                    condition = when (conditionInput) {
                        1 -> "New"
                        2 -> "Used"
                        3 -> "Damage"
                        else -> {
                            println("Invalid condition. Please enter a number between 1 and 3.")
                            continue
                        }
                    } ?: ""
                } while (condition !in listOf("New", "Used", "Damage"))

                println("Add Description of the Product:")
                val description = readLine()!!

                println("Are you sure you want to Enter this product?")
                println("Product: $productName")
                println("Price: Php $price")
                println("Category: $category")
                println("Condition: $condition")
                println("Description:")
                println("\"$description\"")
                println("[1] Yes")
                println("[2] No")

                val confirm = readLine()!!
                if (confirm.equals("1", ignoreCase = true)) {
                    println("===== Product Added Successfully =====")
                    println("Product Number ${numProducts + i}:")
                    println("Product: $productName")
                    println("Price: Php $price")
                    println("Category: $category")
                    println("Condition: $condition")
                    println("Description:")
                    println("\"$description\"")
                } else {
                    println("Product addition cancelled for Product Number ${numProducts + i}")
                }
            }
            numProducts += moreProducts
        } else if (addMore == "2") {
            println("Exiting...")
        }
    }
}
