package practica2023.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import practica2023.ecommerce.repositories.OrderRepository;
import practica2023.ecommerce.repositories.OrdersHasProductsRepository;
import practica2023.ecommerce.repositories.ProductRepository;
import practica2023.ecommerce.bean.OrderContentRest;
import practica2023.ecommerce.bean.OrderRest;
import practica2023.ecommerce.entities.Order;
import practica2023.ecommerce.entities.OrdersHasProducts;
import practica2023.ecommerce.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin

public class OrderController {

    @Autowired
    OrderRepository or;
    @Autowired
    OrdersHasProductsRepository ohpr;
    @Autowired
    ProductRepository pr;

    @GetMapping(value="/orders")
    List<Order> getAllProducts(){
        return or.findAll();
    }

    @GetMapping(value="/orders/{order_id}")
    OrderRest getOrderById(@PathVariable int order_id){

        OrderRest foundOrder = new OrderRest();
        OrderContentRest orderContentRest;
        List<OrderContentRest> foundOrderContent = new ArrayList<>(); //inicializar lista, en getAllx() se inicializa sola pero aquí no

        for (Order order : or.findAll()) {
            if ((order.getOrder_id()) == (order_id)) {

                foundOrder.setOrder_id(order.getOrder_id());
                foundOrder.setStatus(order.getStatus());
                foundOrder.setCustomer_name(order.getCustomer_name());
                foundOrder.setCustomer_email(order.getCustomer_email());
                foundOrder.setCustomer_telephone(order.getCustomer_telephone());
                foundOrder.setAmount(order.getAmount());
                foundOrder.setDate(order.getDate());
                foundOrder.setTime(order.getTime());

                for (OrdersHasProducts ordersHasProducts : ohpr.findAll() ){
                    if ( (ordersHasProducts.getOrder_id()) == (order_id)) {
                        orderContentRest = new OrderContentRest();
                        orderContentRest.setProduct_id(ordersHasProducts.getProduct_id());
                        orderContentRest.setQuantity(ordersHasProducts.getQuantity());
                        orderContentRest.setUnit_price(ordersHasProducts.getUnit_price());

                        foundOrderContent.add(orderContentRest);
                        
                    }
                }

                foundOrder.setOrderContent(foundOrderContent);

                break;
            }
        }
        return foundOrder;
    }


    @PostMapping(value="/orders")
    OrderRest newOrder(@RequestBody OrderRest norder){

        Order plainOrder = new Order();
        plainOrder.setStatus(norder.getStatus());
        plainOrder.setCustomer_name(norder.getCustomer_name());
        plainOrder.setCustomer_email(norder.getCustomer_email());
        plainOrder.setCustomer_telephone(norder.getCustomer_telephone());
        plainOrder.setDate(norder.getDate());
        plainOrder.setTime(norder.getTime());
        or.saveAndFlush(plainOrder);

       OrdersHasProducts thisOrderHasProducts = new OrdersHasProducts();
       double totalAmount = 0;

        for (OrderContentRest ocr : norder.getOrderContent()) {
            
            Optional<Product> optionalProduct = pr.findById(ocr.getProduct_id());

            if (optionalProduct.isPresent()) {

                Product product = optionalProduct.get();

                if ((ocr.getUnit_price()) != (product.getPrice())) {
                    ocr.setUnit_price(product.getPrice());
                }

                thisOrderHasProducts.setOrder_id(plainOrder.getOrder_id());
                thisOrderHasProducts.setProduct_id(ocr.getProduct_id());
                thisOrderHasProducts.setQuantity(ocr.getQuantity());
                thisOrderHasProducts.setUnit_price(product.getPrice());
                ohpr.saveAndFlush(thisOrderHasProducts);

                totalAmount = totalAmount + (ocr.getQuantity())*(product.getPrice());

            } //faltaría el else por si no existe
        }
        
        plainOrder.setAmount(totalAmount);
        or.saveAndFlush(plainOrder);

        norder.setOrder_id(plainOrder.getOrder_id());
        norder.setAmount(totalAmount);
        return norder;
    }

     /*Creo una newOrder --> params: objeto OrderRest (norder) que será proporcionado en el body de la solicitud
    (Spring deserializa el cuerpo de la solicitud y lo pasa a objeto)
    En el método usamos un bean del repository para saveAndFlush el objeto que pasamos por params
    y nos devuelve el objeto recién creado y guardado en la BBDD.
    (el objeto se serializará automáticamente a JSON para la respuesta)*/

}
