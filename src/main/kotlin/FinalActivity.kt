import java.util.Scanner

fun mainMenu() {
    println("""
    ─────────────────────────────────────────────────────────────────
        Welcome to Cookbook Companion: Your Recipe Manager           
    ─────────────────────────────────────────────────────────────────
      [1] » Create a New Recipe                                                           
      [2] » Update an Existing Recipe                                                     
      [3] » Remove a Recipe                                                               
      [4] » View Recipes                                                                  
      [5] » Search Recipes                                                                
      [6] » Exit                                                                          
    ─────────────────────────────────────────────────────────────────
    Enter the number corresponding to your choice: 
    """)
    val choice = readLine()!!.toIntOrNull()
    when (choice) {
        1 -> addRecipe()
        2 -> updateRecipe()
        3 -> removeRecipe()
        4 -> viewRecipe()
        5 -> searchRecipe()
        6 -> System.exit(0)
        else -> println("Invalid choice. Please enter a number between 1 and 6.")
    }
}
fun main() {
    mainMenu()
}
fun addRecipe() {
        println("[1] Add Recipe\n")

        println(
            """
            ────────────────────────────────────────────────────
                      Please Enter the Name of the Recipe      
            ────────────────────────────────────────────────────
        """.trimIndent()
        )
        print("> ")
        var name = readLine()!!

        val existingRecipe = recipes.find { it.name.equals(name, ignoreCase = true) }
        if (existingRecipe != null) {
            println(
                """
                ────────────────────────────────────────────────────
                             Recipe with the Same Name              
                                  Already Exists                    
                ────────────────────────────────────────────────────
            """.trimIndent()
            )
            existingRecipe.displayDetails()
            println("\nPress any key to return to the main menu...")
            readLine()
            return
        }

        println(
            """
            ────────────────────────────────────────────────────
                      Please Enter the Ingredients             
            ────────────────────────────────────────────────────
            (one ingredient per line, press Enter twice when finished)
        """.trimIndent()
        )
        val ingredients = mutableListOf<String>()
        var ingredient: String?
        while (true) {
            ingredient = readLine()!!
            if (ingredient.isBlank()) break
            ingredients.add(ingredient)
        }

        if (ingredients.isEmpty()) {
            println("Error: Ingredients cannot be blank. Please try again.")
            println("\nPress any key to return to the main menu...")
            readLine()
            return
        }

        println(
            """
            ────────────────────────────────────────────────────
                      Please Enter the Instructions            
            ────────────────────────────────────────────────────
            (one instruction per line, press Enter twice when finished)
        """.trimIndent()
        )
        var instructions = ""
        var line: String?
        while (true) {
            line = readLine()!!
            if (line.isBlank()) break
            instructions += line + "\n"
        }

        if (instructions.isBlank()) {
            println("Error: Instructions cannot be blank. Please try again.")
            println("\nPress any key to return to the main menu...")
            readLine()
            return
        }

        println(
            """
            ────────────────────────────────────────────────────
                      Please Enter the Cooking Time            
            ────────────────────────────────────────────────────
        """.trimIndent()
        )
        var cookingTime = readLine()!!.toInt()
        while (cookingTime <= 0) {
            println("Invalid input. Please enter a valid cooking time in minutes:")
            cookingTime = readLine()!!.toInt()
        }

        println(
            """
            ────────────────────────────────────────────────────
                      Please Select a Category              
            ────────────────────────────────────────────────────
            [1] » Appetizers & Snacks
            [2] » Main Dishes
            [3] » Side Dishes
            [4] » Desserts
            [5] » Breakfast & Brunch
            [6] » Beverages
            [7] » Soups & Salads
            ─────────────────────────────────────────────────────────────
        """.trimIndent()
        )

        var categoryInput = readLine()!!
        var category = ""
        while (!categoryInput.matches("[1-7]".toRegex())) {
            println("Invalid choice. Please enter a number between 1 and 7.")
            categoryInput = readLine()!!
        }

        category = when (categoryInput) {
            "1" -> "Appetizers & Snacks"
            "2" -> "Main Dishes"
            "3" -> "Side Dishes"
            "4" -> "Desserts"
            "5" -> "Breakfast & Brunch"
            "6" -> "Beverages"
            else -> "Soups & Salads"
        }

        val newRecipe = Recipe(name, ingredients, instructions, cookingTime, category)

        newRecipe.displayDetails()

        println("\nWould you like to add this recipe? (Y/N): ")
        val confirmInput = readLine()!!
        if (confirmInput.equals("Y", ignoreCase = true)) {
            recipes.add(newRecipe)
            println("\nRecipe added successfully!")
        } else {
            println("\nRecipe not added.")
        }

        println("\nPress any key to return to the main menu...")
        readLine()
        mainMenu()
    }

fun displayWelcomeInterface() {
    println(
        """
        Welcome to Cookbook Companion: Your Recipe Manager

        ----------------------------------------------------
                   Manage your recipes with ease!            
        ----------------------------------------------------
        """.trimIndent()
    )
}

abstract class FoodItem(
    var name: String,
    var ingredients: MutableList<String>,
    var instructions: String,
    var cookingTime: Int,
    var category: String
) {
    abstract fun displayDetails()
}

open class Recipe(
    name: String,
    ingredients: MutableList<String>,
    instructions: String,
    cookingTime: Int,
    category: String
) : FoodItem(name, ingredients, instructions, cookingTime, category) {
    override fun displayDetails() {
        println(
            """
            ────────────────────────────────────────────────────
                             ${name.toUpperCase()}                 
            ────────────────────────────────────────────────────
            Category          |  $category
            Cooking Time      |  $cookingTime minutes
            ────────────────────────────────────────────────────
            Ingredients:
        """.trimIndent()
        )
        ingredients.forEach { ingr ->
            println("- $ingr")
        }
        println(
            """
            ────────────────────────────────────────────────────
            Instructions:
            $instructions
            ────────────────────────────────────────────────────
        """.trimIndent()
        )
    }
}

fun updateRecipe() {
    println("[2] Update Recipe\n")

    if (recipes.isEmpty()) {
        println("There are no recipes to update.")
        println("\nPress any key to return to the main menu...")
        readLine()
        mainMenu()
        return
    }

    println("Please enter the name of the recipe you want to update:")
    var recipeName = readLine()!!

    val recipeToUpdate = recipes.find { it.name.equals(recipeName, ignoreCase = true) }

    if (recipeToUpdate == null) {
        println(
            """
                ────────────────────────────────────────────────────
                          Recipe Not Found                   
                ────────────────────────────────────────────────────
                [1] » Try again
                [2] » List recipes
                [3] » Search recipes
                [4] » Cancel
                ────────────────────────────────────────────────────
            """.trimIndent()
        )

        var option = readLine()!!
        while (!option.matches("[1-4]".toRegex())) {
            println("Invalid choice. Please enter a number between 1 and 4.")
            option = readLine()!!
        }

        when (option) {
            "1" -> updateRecipe()
            "2" -> viewRecipe()
            "3" -> searchRecipe()
            "4" -> mainMenu()
        }
    } else {
        println("Recipe found. What would you like to update?\n")

        var updated = false
        while (!updated) {
            recipeToUpdate.displayDetails()
            println(
                """
        ────────────────────────────────────────────────────
                  What would you like to update           
        ────────────────────────────────────────────────────
        [1] » Recipe Name
        [2] » Ingredients
        [3] » Cooking Instructions
        [4] » Cooking Time
        [5] » Confirm
        [6] » Cancel
        ────────────────────────────────────────────────────
    """.trimIndent()
            )

            print("> ")
            var option = readLine()!!
            when (option) {
                "1" -> {
                    println("Enter the new recipe name: ")
                    recipeName = readLine()!!
                    recipeToUpdate.name = recipeName
                    println("\nName updated!")
                }


                "2" -> {
                    println("Enter the new list of ingredients (one ingredient per line, press Enter twice when finished):")
                    val newIngredients = mutableListOf<String>()
                    var ingredient: String?
                    while (true) {
                        ingredient = readLine()!!
                        if (ingredient.isBlank()) break
                        newIngredients.add(ingredient)
                    }
                    recipeToUpdate.ingredients = newIngredients
                    println("\nIngredients updated!")
                }

                "3" -> {
                    println("Enter the new cooking instructions (press Enter twice to confirm):")
                    var newInstructions = ""
                    var line: String?
                    while (true) {
                        line = readLine()!!
                        if (line.isBlank()) break
                        newInstructions += line + "\n"
                    }
                    recipeToUpdate.instructions = newInstructions
                    println("\nCooking instructions updated!")
                }

                "4" -> {
                    println("Enter the new cooking time (in minutes): ")
                    var newCookingTime = readLine()!!.toInt()
                    while (newCookingTime <= 0) {
                        println("Invalid input. Please enter a valid cooking time in minutes:")
                        newCookingTime = readLine()!!.toInt()
                    }
                    recipeToUpdate.cookingTime = newCookingTime
                    println("\nCooking time updated!")
                }

                "5" -> {
                    println("\nDisplaying the updated recipe details:\n")
                    recipeToUpdate.displayDetails()
                    println("\nWould you like to confirm the update? (Y/N): ")
                    val confirmUpdate = readLine()!!
                    if (confirmUpdate.equals("Y", ignoreCase = true)) {
                        println("\nUpdate confirmed!")
                        updated = true
                    } else {
                        println("\nUpdate cancelled.")
                    }
                }

                "6" -> {
                    println("\nUpdate cancelled.")
                    updated = true  // Exit the loop when the user chooses to cancel
                }

                else -> println("Invalid option.")
            }
        }
    }
}

fun removeRecipe() {
    println("[3] Remove Recipe\n")

    if (recipes.isEmpty()) {
        println("There are no recipes to remove.")
        println("\nPress any key to return to the main menu...")
        readLine()
        mainMenu()
        return
    }

    var validOption = false

    while (!validOption) {
        println("Please enter the name of the recipe you want to remove:")
        val recipeName = readLine()!!

        val recipeToRemove = recipes.find { it.name.equals(recipeName, ignoreCase = true) }

        if (recipeToRemove == null) {
            println(
                """
                ────────────────────────────────────────────────────
                          Recipe Not Found                   
                ────────────────────────────────────────────────────
                [1] » Try again
                [2] » List recipes
                [3] » Search recipes
                [4] » Cancel
                ────────────────────────────────────────────────────
                """.trimIndent()
            )

            print("Enter your choice: ")
            var option = readLine()!!

            var validOption1 = false

            while (!validOption1) {
                println("\nInvalid choice. Please enter a valid option (1-4): ")
                option = readLine()!!

                when (option) {
                    "1" -> removeRecipe()
                    "2" -> viewRecipe()
                    "3" -> searchRecipe()
                    "4" -> mainMenu()
                    else -> println("Invalid choice. Please enter a valid option (1-4).")
                }
            }
        } else {
            println("\nRecipe found:\n")
            recipeToRemove.displayDetails()

            print("\nAre you sure you want to remove '${recipeToRemove.name}'? (Y/N): ")
            val confirmDelete = readLine()!!

            if (confirmDelete.equals("Y", ignoreCase = true)) {
                recipes.remove(recipeToRemove)
                println("\n[Success] The recipe '${recipeToRemove.name}' has been removed successfully!")
            } else {
                println("\nRemoval cancelled.")
            }

            validOption = true
        }
    }

    println("\nPress any key to return to the main menu...")
    readLine()
    mainMenu()
}

class RecipeManager(
    name: String,
    ingredients: MutableList<String>,
    instructions: String,
    cookingTime: Int,
    category: String
) : Recipe(name, ingredients, instructions, cookingTime, category) {
    private val recipes = mutableListOf<Recipe>()

    // Function to view all recipes
    fun viewRecipe() {
        println("[4] View Recipes\n")

        if (recipes.isEmpty()) {
            println("There are no recipes to display.")
            println("\nPress any key to return to the main menu...")
            readLine()
            mainMenu()
            return
        }

        println("List of Recipes:\n")
        recipes.forEachIndexed { index, recipe ->
            println("${index + 1}. ${recipe.name}")
        }

        println("\nPress any key to return to the main menu...")
        readLine()
        mainMenu()
    }

    // Function to search for a recipe by name
    fun searchRecipe() {
        println("[5] Search Recipe\n")

        if (recipes.isEmpty()) {
            println("There are no recipes to search.")
            println("\nPress any key to return to the main menu...")
            readLine()
            mainMenu()
            return
        }

        println("Please enter the name of the recipe you want to search:")
        val searchName = readLine()!!

        val searchResults = recipes.filter { it.name.equals(searchName, ignoreCase = true) }

        if (searchResults.isEmpty()) {
            println("No recipes found with the name '$searchName'.")
        } else {
            println("\nSearch Results:\n")
            searchResults.forEach { recipe ->
                recipe.displayDetails()
            }
        }

        println("\nPress any key to return to the main menu...")
        readLine()
        mainMenu()
    }
    }

val recipes = mutableListOf<Recipe>()
fun viewRecipe() {
    println("[2] View Recipe\n")
    if (recipes.isEmpty()) {
        println("There are no recipes to view.")
        println("\nPress any key to return to the main menu...")
        readLine()
        mainMenu()
        return
    }
    println("────────────────────────────────────────────────────")
    println("              Please Select an Option               ")
    println("────────────────────────────────────────────────────")
    println("[1] » View All Recipes")
    println("[2] » View Recipes by Category")
    println("[3] » Cancel")
    println("────────────────────────────────────────────────────")
    print("\n> ")
    var validOption = false
    while (!validOption) {
        val choice = readLine()!!
        when (choice) {
            "1" -> {
                viewAllRecipes()
                validOption = true
            }
            "2" -> {
                viewRecipesByCategory()
                validOption = true
            }
            "3" -> {
                mainMenu()
                validOption = true
            }
            else -> println("Invalid choice. Please select a valid option.")
        }
    }
}


private fun viewAllRecipes() {
    println("--- All Recipes ---\n")
    recipes.forEachIndexed { index, recipe ->
        println("[${index + 1}] ${recipe.name}")
    }

    println("\nPlease enter the number of the recipe you want to view:")
    val recipeIndex = readLine()!!.toIntOrNull()
    if (recipeIndex != null && recipeIndex >= 1 && recipeIndex <= recipes.size) {
        displayRecipe(recipes[recipeIndex - 1])
    } else {
        println("Invalid input. Please enter a valid number.")
        viewAllRecipes() // Recursive call is okay here to retry input
    }
}

private fun viewRecipesByCategory() {
    println("────────────────────────────────────────────────────")
    println("              Please Select a Category              ")
    println("────────────────────────────────────────────────────")
    println("[1] » Appetizers & Snacks")
    println("[2] » Main Dishes")
    println("[3] » Side Dishes")
    println("[4] » Desserts")
    println("[5] » Breakfast & Brunch")
    println("[6] » Beverages")
    println("[7] » Soups & Salads")
    println("────────────────────────────────────────────────────")
    print("> ")

    val choice = readLine()!!.toIntOrNull()
    if (choice != null && choice >= 1 && choice <= 7) {
        val selectedCategory = when (choice) {
            1 -> "Appetizers & Snacks"
            2 -> "Main Dishes"
            3 -> "Side Dishes"
            4 -> "Desserts"
            5 -> "Breakfast & Brunch"
            6 -> "Beverages"
            7 -> "Soups & Salads"
            else -> ""
        }

        val filteredRecipes = recipes.filter { it.category == selectedCategory }

        if (filteredRecipes.isNotEmpty()) {
            println("--- $selectedCategory Recipes ---\n")
            filteredRecipes.forEachIndexed { index, recipe ->
                println("[${index + 1}] ${recipe.name}")
            }

            println("\nPlease enter the number of the recipe you want to view:")
            val recipeIndex = readLine()!!.toIntOrNull()
            if (recipeIndex != null && recipeIndex >= 1 && recipeIndex <= filteredRecipes.size) {
                displayRecipe(filteredRecipes[recipeIndex - 1])
            } else {
                println("Invalid input. Please enter a valid number.")
            }
        } else {
            println("No recipes found in this category.")
        }
    } else {
        println("Invalid choice. Please enter a number between 1 and 7.")
    }

    println("\nPress any key to return to the main menu...")
    readLine()
    mainMenu()
}

private fun displayRecipe(recipe: Recipe) {
    println("--- ${recipe.name} ---\n")
    recipe.displayDetails()

    println("\nPress any key to return to the main menu...")
    readLine()
    mainMenu()
}



fun searchRecipe() {
    println("────────────────────────────────────────────────────")
    println("            Please Select an Option                ")
    println("────────────────────────────────────────────────────")
    println("[1] » Search by Recipe Name")
    println("[2] » Search by Ingredients")
    println("[3] » Cancel")
    println("────────────────────────────────────────────────────")
    print("\n> ")

    when (val option = readLine()!!) {
        "1" -> {
            println("\nPlease enter the recipe name:")
            val recipeName = readLine()!!
            val matchingRecipesByName = recipes.filter { it.name.equals(recipeName, ignoreCase = true) }
            if (matchingRecipesByName.isNotEmpty()) {
                println("\nMatching Recipes:\n")
                matchingRecipesByName.forEachIndexed { index, recipe ->
                    println("[${index + 1}] ${recipe.name}")
                }
                println("\nSelect a recipe number to view details:")
                val selectedRecipeIndex = readLine()!!.toIntOrNull()
                if (selectedRecipeIndex != null && selectedRecipeIndex > 0 && selectedRecipeIndex <= matchingRecipesByName.size) {
                    println("\nDisplaying Recipe Details:\n")
                    displayRecipe(matchingRecipesByName[selectedRecipeIndex - 1])
                    return
                } else {
                    println("\nInvalid selection. Returning to main menu...")
                }
            } else {
                println("\nNo recipes found with that name.")
            }
        }
        "2" -> {
            println("\nPlease enter ingredients to search for (separated by commas):")
            val inputIngredients = readLine()!!
            val ingredientList = inputIngredients.split(",").map { it.trim() }
            val matchingRecipesByIngredients = recipes.filter { recipe ->
                recipe.ingredients.any { ingredient ->
                    ingredientList.any { it.equals(ingredient, ignoreCase = true) }
                }
            }
            if (matchingRecipesByIngredients.isNotEmpty()) {
                println("\nMatching Recipes:\n")
                matchingRecipesByIngredients.forEachIndexed { index, recipe ->
                    println("[${index + 1}] ${recipe.name}")
                }
                println("\nSelect a recipe number to view details:")
                val selectedRecipeIndex = readLine()!!.toIntOrNull()
                if (selectedRecipeIndex != null && selectedRecipeIndex > 0 && selectedRecipeIndex <= matchingRecipesByIngredients.size) {
                    println("\nDisplaying Recipe Details:\n")
                    displayRecipe(matchingRecipesByIngredients[selectedRecipeIndex - 1])
                    return
                } else {
                    println("\nInvalid selection. Returning to main menu...")
                }
            } else {
                println("\nNo recipes found with those ingredients.")
            }
        }
        "3" -> {
            mainMenu()
            return
        }
        else -> {
            println("Invalid option.")
            searchRecipe()
            return
        }
    }

    println("\nPress any key to return to the main menu...")
    readLine()
    mainMenu()
}


