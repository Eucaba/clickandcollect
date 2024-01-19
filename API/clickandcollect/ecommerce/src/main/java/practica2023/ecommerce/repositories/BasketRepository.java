package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practica2023.ecommerce.entities.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    
}