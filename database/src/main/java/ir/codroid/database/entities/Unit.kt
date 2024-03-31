package ir.codroid.database.entities

enum class Unit(val rawValue: String) {
    KG("kilo_gram"),
    PACKAGE("package"),
    NUMBER("number");

    companion object {
        fun from(findValue: String?): Unit =
            entries.find { it.rawValue == (findValue ?: "") } ?: NUMBER
    }
}