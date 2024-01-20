package practica2023.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import practica2023.ecommerce.repositories.BasketRepository;
import practica2023.ecommerce.repositories.BasketsHasProductsRepository;
import practica2023.ecommerce.repositories.IngredientRepository;
import practica2023.ecommerce.repositories.ProductRepository;
import practica2023.ecommerce.bean.BasketContentRest;
import practica2023.ecommerce.bean.BasketRest;
import practica2023.ecommerce.entities.Basket;
import practica2023.ecommerce.entities.BasketsHasProducts;
import practica2023.ecommerce.entities.Product;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin

public class BasketController {

    @Autowired
    BasketRepository br;
    @Autowired
    BasketsHasProductsRepository bhpr;
    @Autowired
    IngredientRepository ir;
    @Autowired
    ProductRepository pr;

    @GetMapping(value = "/baskets")
    List<Basket> getAllBaskets() {
        return br.findAll();
    }

    @PostMapping(value = "/baskets")
    BasketRest newBasket(@RequestBody BasketRest nbasket) {

        BasketRest newBasket = new BasketRest();
        double newBasketAmount = 0;

        Basket simpleBasket = new Basket();
        simpleBasket.setStatus("basket en uso");
        br.saveAndFlush(simpleBasket);

        for (BasketContentRest bcr : nbasket.getBasketContent()) {

            // comprobamos que el producto existe, si el producto existe:
            // 1) verifico el precio del producto que figura en la basket content (para que
            // se obtenga de la BBDD y no del "exterior")
            // 2) actualizo el precio de la cesta
            // 3) guardo la información correspondiente en la tabla baskets_has_products
            Optional<Product> optionalProduct = pr.findById(bcr.getProduct_id());
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();

                if ((bcr.getUnit_price()) != (product.getPrice())) {
                    bcr.setUnit_price(product.getPrice());
                }

                newBasketAmount = newBasketAmount + (bcr.getQuantity()) * (product.getPrice());

                BasketsHasProducts bhp = new BasketsHasProducts();
                bhp.setBasket_id(simpleBasket.getBasket_id());
                bhp.setProduct_id(bcr.getProduct_id());
                bhp.setQuantity(bcr.getQuantity());
                bhpr.saveAndFlush(bhp);
            } // else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not
              // found");}

            /*
             * ResponseStatusException es una clase en Spring Framework que representa una
             * excepción específica
             * para gestionar errores relacionados con códigos de estado HTTP .
             * Se usa en gral para indicar que se ha producido un error y especificar el
             * código de estado HTTP que debería enviarse como respuesta a la solicitud.
             * Esta clase proporciona constructores que permiten especificar el código de
             * estado HTTP y opcionalmente un mensaje y/o una causa para la excepción.
             */
        }

        newBasket.setBasket_id(simpleBasket.getBasket_id());
        newBasket.setStatus("basket en uso");
        newBasket.setBasketContent(nbasket.getBasketContent());
        newBasket.setBasketAmount(newBasketAmount);
        return newBasket;
    }

    @PutMapping(value = "/baskets")
    BasketRest modifiedBasket(@RequestBody BasketRest mBasket) {

        double mBasketAmount = 0;

        Optional<Basket> optionalBasket = br.findById(mBasket.getBasket_id());

        if (optionalBasket.isPresent()) {
            // lo único que cambia es el basketContent:
            // borramos el basket content anterior alojado de la tabla baskets_has_products
            bhpr.deleteBasketsHasProducstByBasketId(mBasket.getBasket_id());

            for (BasketContentRest bcr : mBasket.getBasketContent()) {

                // reviso que el producto que pida exista
                Optional<Product> optionalProduct = pr.findById(bcr.getProduct_id());
                if (optionalProduct.isPresent()) {

                    // si exite, grabamos la nueva información en la tabla baskets_has_products
                    BasketsHasProducts bhp = new BasketsHasProducts();
                    bhp.setBasket_id(mBasket.getBasket_id());
                    bhp.setProduct_id(bcr.getProduct_id());
                    bhp.setQuantity(bcr.getQuantity());
                    bhpr.saveAndFlush(bhp);

                    // aprovechamos que estamos recorriendo el basketContent de mBasket,
                    // y revisamos que los precios que llegan de "fuera" sean acorde a la BBDD (si
                    // no, corregimos)
                    Product product = optionalProduct.get();
                    if ((bcr.getUnit_price()) != (product.getPrice())) {
                        bcr.setUnit_price(product.getPrice());
                    }

                    // Actualizo el precio total (en base a los precios de la BBDD)
                    mBasketAmount = mBasketAmount + (bcr.getQuantity()) * (product.getPrice());

                } // al final del bloque vendría un else {} para manejar el error cuando sepa CÓMO
            }
            mBasket.setBasketAmount(mBasketAmount);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Basket not found");
        }

        return mBasket;
    }

    /*
     * @PutMapping(value = "/baskets")
     * Basket updateBasket(@RequestBody Basket updatedBasket) {
     * return br.findById(updatedBasket.getBasket_id())
     * .map(basket -> {
     * basket.setStatus(updatedBasket.getStatus());
     * return br.saveAndFlush(basket);
     * })
     * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
     * "Basket not found"));
     * }
     * .map se usa cuando es "optional" (puede estar o no)
     * hace que lo que hay entre paréntesis se intente realizar sólo si el optional
     * no está vacío,
     * si está vacío lanzamos excepción. Si no lo hacemos lo que pase al venir vacío
     * el optional
     * dependerá del entorno y puede variar
     */

}