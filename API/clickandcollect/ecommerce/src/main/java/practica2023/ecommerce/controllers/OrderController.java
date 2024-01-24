package practica2023.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import practica2023.ecommerce.repositories.OrderRepository;
import practica2023.ecommerce.repositories.OrdersHasProductsRepository;
import practica2023.ecommerce.repositories.ProductRepository;
import practica2023.ecommerce.bean.OrderContentRest;
import practica2023.ecommerce.bean.OrderRest;
import practica2023.ecommerce.entities.Order;
import practica2023.ecommerce.entities.OrdersHasProducts;
import practica2023.ecommerce.entities.Product;

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

    @PostMapping(value = "/orders")
    OrderRest newOrder(@RequestBody OrderRest norder) {

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

                totalAmount = totalAmount + (ocr.getQuantity()) * (product.getPrice());

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
            }
        }

        plainOrder.setAmount(totalAmount);
        or.saveAndFlush(plainOrder);

        norder.setOrder_id(plainOrder.getOrder_id());
        norder.setAmount(totalAmount);
        return norder;
    }
}
