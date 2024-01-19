package practica2023.ecommerce.bean;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import lombok.Data;

@Data
public class OrderRest {
    
    private int order_id;
    private String status;
    private String customer_name;
    private String customer_email;
    private String customer_telephone;
    private double amount;
    private Date date;
    private Time time;
    private List<OrderContentRest> orderContent;
}