package expert.springframework.bootstrap;

import expert.springframework.domain.*;
import expert.springframework.repositories.CategoryRepository;
import expert.springframework.repositories.RecipeRepository;
import expert.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs

        Optional<UnitOfMeasure> unidadUomOptional = unitOfMeasureRepository.findByDescription("Unidad");

        if (!unidadUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cucharadaUomOptional = unitOfMeasureRepository.findByDescription("Cucharada");

        if (!cucharadaUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cucharaditaUomOptional = unitOfMeasureRepository.findByDescription("Cucharadita");

        if (!cucharaditaUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tazaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Taza");

        if (!tazaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pizcaUomOptional = unitOfMeasureRepository.findByDescription("Pizca");

        if (!pizcaUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> onzaUomOptional = unitOfMeasureRepository.findByDescription("Onza");

        if (!onzaUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        //get optionals
        UnitOfMeasure unidadUom = unidadUomOptional.get();
        UnitOfMeasure cucharadaUom = cucharadaUomOptional.get();
        UnitOfMeasure cucharaditaUom = cucharaditaUomOptional.get();
        UnitOfMeasure tazaUom = tazaSpoonUomOptional.get();
        UnitOfMeasure pizcaUom = pizcaUomOptional.get();
        UnitOfMeasure onzaUom = onzaUomOptional.get();

        //get Categories
        Optional<Category> criollaCategoryOptional = categoryRepository.findByDescription("Criolla");

        if (!criollaCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> marinaCategoryOptional = categoryRepository.findByDescription("Marina");

        if (!marinaCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category criollaCategory = criollaCategoryOptional.get();
        Category marinaCategory = marinaCategoryOptional.get();

        //Estofado de pollo
        Recipe estofadoRecipe = new Recipe();
        estofadoRecipe.setDescription("Estofado de Pollo");
        estofadoRecipe.setPrepTime(10);
        estofadoRecipe.setCookTime(0);
        estofadoRecipe.setDifficulty(Difficulty.EASY);
        estofadoRecipe.setDirections("1. Un rato antes de comenzar a cocinar, sazona las piezas de pollo con sal, pimienta, comino y un diente de ajo rallado" +
                "\n" +
                "2. Calienta el aceite en una sartén y lleva el pollo a sofreír. Dóralo muy bien por todos lados, pero no es necesario que esté completamente cocido por dentro, ya que luego le daremos una segunda cocción.)" +
                "\n" +
                "3. Reserva cuando esté cocido de manera uniforme. \n" +
                "4. En la misma sartén donde sofreíste el pollo, añade la cebolla morada. Cuando comience a cristalizar, agrega el resto del ajo ya machacado o rallado. \n" +
                "5. Una vez que la cebolla y el ajo comiencen a soltar el sabor, vierte la pasta de ají panca. Revuelve bien. \n" +
                "6. Mientras, licua los ajíes amarillos con un poco de agua y agrega esa pasta a la cocción. Mezcla para integrar todos los sabores. \n" +
                "7. Cocina por dos minutos y vierte los tomates ya licuados. \n" +
                "8. Es el momento de añadir el caldo y las piezas de pollo para dejar hervir un rato. \n" +
                "9. Adiciona las papas ya cortadas y la zanahoria en ruedas a la cocción del pollo. Tápala y deja que el caldo se reduzca un poco y se suavicen papas y zanahoria. Todo a fuego medio. \n" +
                "10. Por último, añade los guisantes y corrige la sal de la preparación. Puedes sazonar con un poco más de especias y una hoja de laurel. \n" +
                "11. Una vez listo el estofado de pollo peruano, sirve caliente con arroz blanco y disfruta. \n" +
                "Read more: https://www.recetasgratis.net/receta-de-estofado-de-pollo-peruano-74875.html");

        Notes estofadoNotes = new Notes();
        estofadoNotes.setRecipeNotes("Si te ha gustado la receta de Estofado de pollo peruano,\n" +
                "te sugerimos que entres en nuestra categoría de Recetas de Pollo. \n" +
                "También puedes visitar una selección de las mejores recetas peruanas.\n" +
                "Read more: https://www.recetasgratis.net/receta-de-estofado-de-pollo-peruano-74875.html");
        estofadoNotes.setRecipe(estofadoRecipe);
        estofadoRecipe.setNotes(estofadoNotes);

        estofadoRecipe.getIngredients().add(new Ingredient("media taza de ají panca", new BigDecimal(0.5), tazaUom, estofadoRecipe));
        estofadoRecipe.getIngredients().add(new Ingredient("guisantes verdes", new BigDecimal(4), cucharadaUom, estofadoRecipe));
        estofadoRecipe.getIngredients().add(new Ingredient("piezas de pollo", new BigDecimal(4), unidadUom, estofadoRecipe));
        estofadoRecipe.getIngredients().add(new Ingredient("tomates licuados", new BigDecimal(4), unidadUom, estofadoRecipe));
        estofadoRecipe.getIngredients().add(new Ingredient("papas grandes amarillas cortadas", new BigDecimal(2), unidadUom, estofadoRecipe));
        estofadoRecipe.getIngredients().add(new Ingredient("cebolla morada", new BigDecimal(1), unidadUom, estofadoRecipe));
        estofadoRecipe.getCategories().add(criollaCategory);
        //add to return list
        recipes.add(estofadoRecipe);


        return recipes;
    }


}
