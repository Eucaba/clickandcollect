package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practica2023.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}

/*interactúa con la BBDD sin tener que escribir sentencias SQL,
trae desde JPA métodos para el CRUD ya (p.e. saveAndFlush, findAll, findById...*/