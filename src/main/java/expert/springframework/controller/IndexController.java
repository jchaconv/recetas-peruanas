package expert.springframework.controller;

import expert.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /*private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }*/

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {

        log.debug("=== Getting index page ===");

        model.addAttribute("recipes", recipeService.getRecipes());

        /*
        Optional<Category> categoryOptional = categoryRepository.findByDescription("Criolla");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cucharada");

        System.out.println("Category id is: " + categoryOptional.get().getId());
        System.out.println("Uom id is: " + unitOfMeasureOptional.get().getId());
        */
        return "index";
    }

}
