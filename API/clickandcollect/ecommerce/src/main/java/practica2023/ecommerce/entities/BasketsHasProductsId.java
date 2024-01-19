package practica2023.ecommerce.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class BasketsHasProductsId implements Serializable{

    @Getter @Setter
    private int basket_id;

    @Getter @Setter
    private int product_id;
    
}
