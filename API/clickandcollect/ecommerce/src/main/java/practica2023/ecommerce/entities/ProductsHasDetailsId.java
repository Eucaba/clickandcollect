package practica2023.ecommerce.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ProductsHasDetailsId implements Serializable {

    @Getter @Setter
    private int product_id;

    @Getter @Setter
    private int detail_id;
    
}
