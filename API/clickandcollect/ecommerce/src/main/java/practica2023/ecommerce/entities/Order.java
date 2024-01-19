package practica2023.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor

public class Order {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private String customer_name;

    @Getter @Setter
    private String customer_email;

    @Getter @Setter
    private String customer_telephone;

    @Getter @Setter
    private double amount;
    
    @Getter @Setter
    private Date date;

    @Getter @Setter
    private Time time;
}
