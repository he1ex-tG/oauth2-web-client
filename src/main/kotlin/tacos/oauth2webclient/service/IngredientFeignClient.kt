package tacos.oauth2webclient.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import tacos.oauth2webclient.controller.IngredientRest
import tacos.oauth2webclient.entity.Ingredient

@FeignClient(
    name = "IngredientClient",
    url = "http://localhost:8080/api/ingredients",
    configuration = [IngredientFeignConfig::class]
)
interface IngredientFeignClient : IngredientRest {

    @GetMapping
    override fun findAll(): List<Ingredient>

    @PostMapping
    override fun addIngredient(ingredient: Ingredient): Ingredient

    @DeleteMapping("/{id}")
    override fun deleteIngredient(@PathVariable("id") id: String)
}