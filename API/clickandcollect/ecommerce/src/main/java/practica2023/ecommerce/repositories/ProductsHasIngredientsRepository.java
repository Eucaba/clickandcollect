package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import practica2023.ecommerce.entities.ProductsHasIngredients;
import practica2023.ecommerce.entities.ProductsHasIngredientsId;

public interface ProductsHasIngredientsRepository extends JpaRepository<ProductsHasIngredients, ProductsHasIngredientsId> {
    
}