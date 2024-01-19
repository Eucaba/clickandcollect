package practica2023.ecommerce.bean;

import java.util.List;

import lombok.Data;

@Data
public class BasketRest {

    private int basket_id;
    private String status;
    private List<BasketContentRest> basketContent;
    private double basketAmount;

}
