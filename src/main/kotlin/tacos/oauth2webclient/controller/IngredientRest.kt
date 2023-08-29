package tacos.oauth2webclient.controller

import org.springframework.web.bind.annotation.PathVariable
import tacos.oauth2webclient.entity.Ingredient

interface IngredientRest {

    fun findAll(): List<Ingredient>
    fun addIngredient(ingredient: Ingredient): Ingredient
    fun deleteIngredient(@PathVariable("id") id: String)
}