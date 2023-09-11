package tacos.oauth2webclient.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tacos.oauth2webclient.entity.Ingredient
import tacos.oauth2webclient.feign.IngredientsFeignClient

@RestController
@RequestMapping(path = ["/api/ingredients"], produces = ["application/json"])
class IngredientsRestImpl(
    val ingredientsFeignClient: IngredientsFeignClient
) : IngredientsRest {

    @GetMapping
    override fun findAll(): List<Ingredient> {
        return ingredientsFeignClient.findAll()
    }

    @PostMapping
    override fun addIngredient(@RequestBody ingredient: Ingredient): Ingredient {
        return ingredientsFeignClient.addIngredient(ingredient)
    }

    @DeleteMapping("/{id}")
    override fun deleteIngredient(@PathVariable("id") id: String) {
        return ingredientsFeignClient.deleteIngredient(id)
    }
}