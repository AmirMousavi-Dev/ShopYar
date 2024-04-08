package ir.codroid.merchandise_domain.model

data class Merchandise(
    val id: Int? = null,
    val name: String,
    val purchasePrice: Int,
    val salesPrice: Int,
    val code: String,
    val countUnit: CountUnit,
    val image: String?,
    val count: Double,
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$name$code",
            "$name $code",
            name,
            code,
            "$count"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
