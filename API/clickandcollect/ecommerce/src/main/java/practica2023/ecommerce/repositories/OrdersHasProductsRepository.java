package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import practica2023.ecommerce.entities.OrdersHasProducts;
import practica2023.ecommerce.entities.OrdersHasProductsId;

public interface OrdersHasProductsRepository extends JpaRepository<OrdersHasProducts, OrdersHasProductsId> {
    
}