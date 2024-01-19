package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practica2023.ecommerce.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    
}