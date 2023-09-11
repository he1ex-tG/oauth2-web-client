package tacos.oauth2webclient.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import tacos.oauth2webclient.controller.IngredientsRest
import tacos.oauth2webclient.entity.Ingredient

@Controller
@RequestMapping(path = ["/web/ingredients"])
class Ingredients(
    private val ingredientsRest: IngredientsRest
) {

    @ModelAttribute(name = "newIngredient")
    fun newIngredient(): Ingredient {
        return Ingredient()
    }

    @ModelAttribute
    fun listIngredients(model: Model) {
        val listIngredients = ingredientsRest.findAll()
        model.addAttribute("ingredients", listIngredients)
        model.addAttribute("ingredientTypes", Ingredient.Type.values())
    }

    @GetMapping
    fun getAdminIngredients(): String {
        return "adminIngredients"
    }

    @PostMapping
    fun postNewIngredient(
        @ModelAttribute("newIngredient") ingredient: Ingredient
    ): String {
        ingredientsRest.addIngredient(ingredient)
        val uri = MvcUriComponentsBuilder.fromMethodName(
            this::class.java,
            this::getAdminIngredients.name
        ).build().path
        return "redirect:$uri"
    }
}