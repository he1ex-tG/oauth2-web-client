package tacos.oauth2webclient.controller

import tacos.oauth2webclient.entity.Ingredient

interface IngredientsRest {

    fun findAll(): List<Ingredient>
    fun addIngredient(ingredient: Ingredient): Ingredient
    fun deleteIngredient(id: String)
}