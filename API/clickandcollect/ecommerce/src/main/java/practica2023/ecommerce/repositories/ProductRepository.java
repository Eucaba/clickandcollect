package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practica2023.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}

/*interactúa con la BBDD sin tener que escribir sentencias SQL,
trae desde JPA métodos para el CRUD ya (p.e. saveAndFlush, findAll, findById...*/

/* JpaRepository<Product, Integer>
JpaRepository<Entidad, tipo de dato del PK o clave comb.>
 */