package practica2023.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import practica2023.ecommerce.repositories.IngredientRepository;
import practica2023.ecommerce.entities.Ingredient;

import java.util.List;

@RestController
@CrossOrigin

public class IngredientController {

    @Autowired
    IngredientRepository ir;

    @GetMapping(value="/ingredients")
    List<Ingredient> getAllIngredients(){
        return ir.findAll();
    }
    
}
