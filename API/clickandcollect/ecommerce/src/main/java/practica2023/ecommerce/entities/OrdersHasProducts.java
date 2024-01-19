package practica2023.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders_has_products")
@IdClass(OrdersHasProductsId.class)
@AllArgsConstructor
@NoArgsConstructor
public class OrdersHasProducts {

    @Id
    @Getter @Setter
    private int order_id;
    
    @Id
    @Getter @Setter
    private int product_id;

    @Getter @Setter
    private int quantity;

    @Getter @Setter
    private double unit_price;
    
}