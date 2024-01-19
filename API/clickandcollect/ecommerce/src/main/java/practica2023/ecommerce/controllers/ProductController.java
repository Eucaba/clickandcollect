package practica2023.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import practica2023.ecommerce.repositories.ProductRepository;
import practica2023.ecommerce.repositories.ProductsHasDetailsRepository;
import practica2023.ecommerce.repositories.ProductsHasIngredientsRepository;
import practica2023.ecommerce.repositories.DetailRepository;
import practica2023.ecommerce.repositories.IngredientRepository;
import practica2023.ecommerce.bean.ProductRest;
import practica2023.ecommerce.bean.ProductRestBase;
import practica2023.ecommerce.entities.Detail;
import practica2023.ecommerce.entities.Ingredient;
import practica2023.ecommerce.entities.Product;
import practica2023.ecommerce.entities.ProductsHasDetails;
import practica2023.ecommerce.entities.ProductsHasIngredients;

import java.util.ArrayList;
import java.util.List;

@RestController
/*
 * Todos los métodos maneja HTTP y el resultado no depende de ninguna vista (pq
 * es JSON)
 */
@CrossOrigin
/* para evitar problemas de CORS */

public class ProductController {

    @Autowired
    ProductRepository pr;
    @Autowired
    IngredientRepository ir;
    @Autowired
    DetailRepository dr;
    @Autowired
    ProductsHasIngredientsRepository phir;
    @Autowired
    ProductsHasDetailsRepository phdr;
    /*
     * Inyección automática de dependencias.
     * Inyecta un "bean" de ProductRepository en el controlador (método de aquí).
     * Bean = objeto/instancia
     * Es decir: pr funcionará igual que: ProductRepository pr = new
     * ProductRepository();
     */

    @GetMapping /* este va a ruta raíz, es sólo para comprobar a lo cutre */
    String hola() {
        return "Hola, soc el controlador de productes.";
    }

    @GetMapping(value = "/products") /* no passa res si ha l'API doc posa Array i fem una List */
    List<ProductRestBase> getAllProducts() {
        ProductRestBase productBase;
        List<ProductRestBase> lProductBase;

        lProductBase = new ArrayList<ProductRestBase>();
        for (Product product : pr.findAll()) {
            productBase = new ProductRestBase();
            productBase.setProduct_id(product.getProduct_id());
            productBase.setName(product.getName());
            productBase.setImage_ref(product.getImage_ref());
            productBase.setPrice(product.getPrice());

            lProductBase.add(productBase);
        }
        return lProductBase;
    }

    @GetMapping(value = "/products/{product_id}")
    ProductRest getProductById(@PathVariable int product_id) {
        ProductRest foundProduct = new ProductRest();
        List<Ingredient> thisProductIngredients = new ArrayList<>();
        List<Detail> thisProductDetails = new ArrayList<>();

        for (Product product : pr.findAll()) {
            if ((product.getProduct_id()) == (product_id)) {
                foundProduct.setProduct_id(product.getProduct_id());
                foundProduct.setName(product.getName());
                foundProduct.setImage_ref(product.getImage_ref());
                foundProduct.setPrice(product.getPrice());
                foundProduct.setDescription(product.getDescription());
                break;
            }
        }

        for (ProductsHasIngredients phi : phir.findAll()) {
            if ((phi.getProduct_id()) == (product_id)) {
                Ingredient i = new Ingredient();
                i.setIngredient_id(phi.getIngredient_id());
                for (Ingredient ingredient : ir.findAll()) {
                    if ((ingredient.getIngredient_id()) == (phi.getIngredient_id())) {
                        i.setIngredient(ingredient.getIngredient());
                    }
                }
                thisProductIngredients.add(i);
            }
        }
        foundProduct.setProductIngredients(thisProductIngredients);

        for (ProductsHasDetails phd : phdr.findAll()) {
            if ((phd.getProduct_id()) == (product_id)) {
                Detail d = new Detail();
                d.setDetail_id(phd.getDetail_id());
                for (Detail detail : dr.findAll()) {
                    if ((detail.getDetail_id()) == (phd.getDetail_id())) {
                        d.setAttribute(detail.getAttribute());
                    }
                }
                thisProductDetails.add(d);
            }
        }
        foundProduct.setProductDetails(thisProductDetails);

        return foundProduct;
    }
}