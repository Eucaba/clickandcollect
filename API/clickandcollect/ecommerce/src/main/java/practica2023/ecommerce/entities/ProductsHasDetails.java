package practica2023.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="products_has_details")
@IdClass(ProductsHasDetailsId.class)
public class ProductsHasDetails {

    @Id
    @Getter @Setter
    private int product_id;

    @Id
    @Getter @Setter
    private int detail_id;
    
}
