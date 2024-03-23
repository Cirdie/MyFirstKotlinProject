import java.util.Scanner
import java.util.logging.Logger

val logger = Logger.getLogger("CookbookCompanionLogger")

fun log(message: String) {
    logger.info(message)
}

fun mainMenu() {
    logger.info("Main menu displayed.")
    println(
        """
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
    """
    )
    val choice = readLine()!!.toIntOrNull()
    when (choice) {
        1 -> {
            logger.info("User chose to create a new recipe.")
            addRecipe()
        }

        2 -> {
            logger.info("User chose to update an existing recipe.")
            updateRecipe()
        }

        3 -> {
            logger.info("User chose to remove a recipe.")
            removeRecipe()
        }

        4 -> {
            logger.info("User chose to view recipes.")
            viewRecipe()
        }

        5 -> {
            logger.info("User chose to search for recipes.")
            searchRecipe()
        }

        6 -> {
            logger.info("User chose to exit.")
            System.exit(0)
        }

        else -> {
            logger.warning("Invalid choice entered.")
            println("Invalid choice. Please enter a number between 1 and 6.")
        }
    }
}

    fun addRecipe() {
        print("[1] Add Recipe\n")

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
            logger.warning(
                """
            ────────────────────────────────────────────────────
                         Recipe with the Same Name              
                              Already Exists                    
               ────────────────────────────────────────────────────
        """.trimIndent()
            )
            existingRecipe.displayDetails()
            logger.info("\nPress any key to return to the main menu...")
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
            logger.warning("Error: Ingredients cannot be blank. Please try again.")
            logger.info("\nPress any key to return to the main menu...")
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
            logger.warning("Error: Instructions cannot be blank. Please try again.")
            logger.info("\nPress any key to return to the main menu...")
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
            logger.info("Invalid input. Please enter a valid cooking time in minutes:")
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
            logger.info("Invalid choice. Please enter a number between 1 and 7.")
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

        logger.warning("\nWould you like to add this recipe? (Y/N): ")
        val confirmInput = readLine()!!
        if (confirmInput.equals("Y", ignoreCase = true)) {
            recipes.add(newRecipe)
            logger.info("\nRecipe added successfully!")
        } else {
            logger.info("\nRecipe not added.")
        }

        logger.info("\nPress any key to return to the main menu...")
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
    print("[2] Update Recipe\n")
    if (recipes.isEmpty()) {
        logger.info("There are no recipes to update.")
        logger.info("\nPress any key to return to the main menu...")
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
            logger.info("Invalid choice. Please enter a number between 1 and 4.")
            option = readLine()!!
        }
        when (option) {
            "1" -> updateRecipe()
            "2" -> viewRecipe()
            "3" -> searchRecipe()
            "4" -> mainMenu()
        }
    } else {
        logger.info("Recipe found. What would you like to update?\n")
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
                    logger.info("\nName updated!")
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
                    logger.info("\nIngredients updated!")
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
                    logger.info("\nCooking instructions updated!")
                }
                "4" -> {
                   println("Enter the new cooking time (in minutes): ")
                    var newCookingTime = readLine()!!.toInt()
                    while (newCookingTime <= 0) {
                        println("Invalid input. Please enter a valid cooking time in minutes:")
                        newCookingTime = readLine()!!.toInt()
                    }
                    recipeToUpdate.cookingTime = newCookingTime
                    logger.info("\nCooking time updated!")
                }
                "5" -> {
                    println("\nDisplaying the updated recipe details:\n")
                    recipeToUpdate.displayDetails()
                    logger.warning("\nWould you like to confirm the update? (Y/N): ")
                    val confirmUpdate = readLine()!!
                    if (confirmUpdate.equals("Y", ignoreCase = true)) {
                        logger.info("\nUpdate confirmed!")
                        updated = true
                    } else {
                        logger.info("\nUpdate cancelled.")
                    }
                }
                "6" -> {
                    logger.info("\nUpdate cancelled.")
                    updated = true  // Exit the loop when the user chooses to cancel
                }
                else -> logger.info("Invalid option.")
            }
        }
    }
}
fun removeRecipe() {
    print("[3] Remove Recipe\n")
    if (recipes.isEmpty()) {
        println("There are no recipes to remove.")
        logger.info("\nPress any key to return to the main menu...")
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
                logger.warning("\nInvalid choice. Please enter a valid option (1-4): ")
                option = readLine()!!
                when (option) {
                    "1" -> removeRecipe()
                    "2" -> viewRecipe()
                    "3" -> searchRecipe()
                    "4" -> mainMenu()
                    else -> logger.warning("Invalid choice. Please enter a valid option (1-4).")
                }
            }
        } else {
            println("\nRecipe found:\n")
            recipeToRemove.displayDetails()
            logger.warning("\nAre you sure you want to remove '${recipeToRemove.name}'? (Y/N): ")
            val confirmDelete = readLine()!!
            if (confirmDelete.equals("Y", ignoreCase = true)) {
                recipes.remove(recipeToRemove)
                println("\n[Success] The recipe '${recipeToRemove.name}' has been removed successfully!")
            } else {
                logger.info("\nRemoval cancelled.")
            }
            validOption = true
        }
    }
    logger.info("\nPress any key to return to the main menu...")
    readLine()
    mainMenu()
}
fun viewRecipe() {
    print("[4] View Recipe\n")
    if (recipes.isEmpty()) {
        println("There are no recipes to view.")
        logger.info("\nPress any key to return to the main menu...")
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

class RecipeManager(
    name: String,
    ingredients: MutableList<String>,
    instructions: String,
    cookingTime: Int,
    category: String
) : Recipe(name, ingredients, instructions, cookingTime, category) {
    private val recipes = mutableListOf<Recipe>()
    private val logger = Logger.getLogger("RecipeLogger")

    fun searchRecipe() {
        print("[5] Search Recipe\n")

        if (recipes.isEmpty()) {
            println("There are no recipes to search.")
            logger.info("\nPress any key to return to the main menu...")
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

        logger.info("\nPress any key to return to the main menu...")
        readLine()
        mainMenu()
    }

    private fun mainMenu() {
        // Implement main menu logic here
    }
}

val recipes = mutableListOf<Recipe>()

// Other functions...

fun main() {
    val logMessage = "Application started."
    log(logMessage)
    println(logMessage)
    mainMenu()
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
        logger.warning("Invalid input. Please enter a valid number.")
        viewAllRecipes() // Recursive call is okay here to retry input
    }
}
fun viewRecipesByCategory() {
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
                logger.warning("Invalid input. Please enter a valid number.")
            }
        } else {
            println("No recipes found in this category.")
        }
    } else {
        println("Invalid choice. Please enter a number between 1 and 7.")
    }
    logger.info("\nPress any key to return to the main menu...")
    readLine()
    mainMenu()
}
private fun displayRecipe(recipe: Recipe) {
   println("--- ${recipe.name} ---\n")
    recipe.displayDetails()
    logger.info("\nPress any key to return to the main menu...")
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
    println("\n> ")
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
                    logger.info("\nInvalid selection. Returning to main menu...")
                }
            } else {
                logger.info("\nNo recipes found with that name.")
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
                    logger.info("\nInvalid selection. Returning to main menu...")
                }
            } else {
                logger.info("\nNo recipes found with those ingredients.")
            }
        }
        "3" -> {
            mainMenu()
            return
        }
        else -> {
            logger.warning("Invalid option.")
            searchRecipe()
            return
        }
    }
    logger.info("\nPress any key to return to the main menu...")
    readLine()
    mainMenu()
}
