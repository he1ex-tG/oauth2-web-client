package tacos.oauth2webclient.entity

data class Ingredient(
    var id: String = "",
    var name: String = "",
    var type: Type = Type.WRAP,
) {
    enum class Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE,
    }
}