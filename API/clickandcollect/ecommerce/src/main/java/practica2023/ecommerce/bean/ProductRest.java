package practica2023.ecommerce.bean;

import java.util.List;

import lombok.Data;
import practica2023.ecommerce.entities.Detail;
import practica2023.ecommerce.entities.Ingredient;

@Data
public class ProductRest extends ProductRestBase {

    private String description;
    List<Ingredient> productIngredients;
    List<Detail> productDetails;

}
