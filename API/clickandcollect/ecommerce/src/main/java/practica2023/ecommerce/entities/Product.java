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

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor //necesario sí o sí para poder instanciar objetos de la entidad mediante reflexión

public class Product {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Primary Key Auto Incremental
    private int product_id;

    @Getter @Setter
    private String name;
    /* anotaciones sustituyen
    public String getName() {return this.name;} y
    public void setName(String name) {this.name = name;} */

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String image_ref;

    @Getter @Setter
    private double price;

    @Getter @Setter
    private int stock;
    
}
