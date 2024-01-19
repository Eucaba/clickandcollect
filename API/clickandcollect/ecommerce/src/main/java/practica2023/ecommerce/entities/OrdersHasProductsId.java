package practica2023.ecommerce.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/* Esto es una entidad "auxiliar" para tablas intermedias de x_has_y ya que estas se crean sin PK AI,
los registros se manejan con una especie de clave compuesta (foreign PK + foreign PK)
que es esta entidad. 
La entidad se nombra acabando en Id, y es imprescindible anotación correspondiente
en la entidad de la tambla intermedia: @IdClass(EjemploId.class)*/

public class OrdersHasProductsId implements Serializable {

    @Getter @Setter
    private int order_id;
    
    @Getter @Setter
    private int product_id;
    
}
