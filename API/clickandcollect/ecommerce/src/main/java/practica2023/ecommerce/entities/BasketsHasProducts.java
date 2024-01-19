package practica2023.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="baskets_has_products")
@IdClass(BasketsHasProductsId.class)
public class BasketsHasProducts {

    @Id
    @Getter @Setter
    private int basket_id;

    @Id
    @Getter @Setter
    private int product_id;

    @Getter @Setter
    private int quantity;
    
}
