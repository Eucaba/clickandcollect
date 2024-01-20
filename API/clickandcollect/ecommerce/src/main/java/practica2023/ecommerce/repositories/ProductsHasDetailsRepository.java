package practica2023.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import practica2023.ecommerce.entities.ProductsHasDetails;
import practica2023.ecommerce.entities.ProductsHasDetailsId;

public interface ProductsHasDetailsRepository extends JpaRepository<ProductsHasDetails, ProductsHasDetailsId> {

    @Query("select phd from ProductsHasDetails phd where product_id = :productId")
    List<ProductsHasDetails> findProductsHasDetailsByProductId(@Param("productId") int productId);
    
}
