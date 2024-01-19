package practica2023.ecommerce.bean;

import lombok.Data;

@Data //substitueix @CrossOrigin, @RestController, etc Cal import lombok!
public class ProductRestBase {
    
    private int product_id;
    private String name;
    private double price;
    private String image_ref;
}
