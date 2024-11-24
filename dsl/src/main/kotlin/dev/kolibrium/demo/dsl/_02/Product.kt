package dev.kolibrium.demo.dsl._02

enum class Product(val productName: String) {
    BACKPACK("Sauce Labs Backpack"),
    BIKE_LIGHT("Sauce Labs Bike Light"),
    BOLT_T_SHIRT("Sauce Labs Bolt T-Shirt"),
    FLEECE_JACKET("Sauce Labs Fleece Jacket"),
    T_SHIRT_RED("Test.allTheThings() T-Shirt (Red)"),
    ONESIE("Sauce Labs Onesie");

    val locatorName: String
        get() = name.lowercase().replace("_", "-")
}