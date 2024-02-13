fun main() {

    for (numberOfGroup in 1..4) {
    print("What is Your Name? ")
    var name = readLine()

    print("What is Your Middle Initial? ")
    var middlename = readLine()

    print("What is Your Last name? ")
    var lastname = readLine()

    print("What is Your Age? ")
    var age = readLine()?.toInt() ?:0

    println("Your Name is $name $middlename. $lastname")
    println("$age years old")

    }

}






