package practica2023.ecommerce.bean;

import lombok.Data;

@Data
public class OrderContentRest {

    private int product_id;
    private int quantity;
    private double unit_price;
    
}
