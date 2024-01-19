package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practica2023.ecommerce.entities.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
    
}