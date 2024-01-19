package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import practica2023.ecommerce.entities.ProductsHasDetails;
import practica2023.ecommerce.entities.ProductsHasDetailsId;

public interface ProductsHasDetailsRepository extends JpaRepository<ProductsHasDetails, ProductsHasDetailsId> {
    
}
