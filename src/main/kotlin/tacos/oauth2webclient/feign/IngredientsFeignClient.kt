package tacos.oauth2webclient.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import tacos.oauth2webclient.controller.IngredientsRest
import tacos.oauth2webclient.entity.Ingredient

@FeignClient(
    name = "IngredientClient",
    url = "http://localhost:8080/api/ingredients",
    configuration = [IngredientsFeignClientConfiguration::class]
)
interface IngredientsFeignClient : IngredientsRest {

    @GetMapping
    override fun findAll(): List<Ingredient>

    @PostMapping
    override fun addIngredient(ingredient: Ingredient): Ingredient

    @DeleteMapping("/{id}")
    override fun deleteIngredient(@PathVariable("id") id: String)
}