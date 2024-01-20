package practica2023.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import practica2023.ecommerce.entities.ProductsHasIngredients;
import practica2023.ecommerce.entities.ProductsHasIngredientsId;

public interface ProductsHasIngredientsRepository extends JpaRepository<ProductsHasIngredients, ProductsHasIngredientsId> {
    
    @Query("select phi from ProductsHasIngredients phi where product_id = :productId")
    List<ProductsHasIngredients> findProductsHasIngredientsByProductId(@Param("productId") int productId);
}