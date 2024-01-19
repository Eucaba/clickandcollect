package practica2023.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import practica2023.ecommerce.repositories.DetailRepository;
import practica2023.ecommerce.entities.Detail;

import java.util.List;

@RestController
/* Todos los métodos maneja HTTP y el resultado no depende de ninguna vista (pq es JSON) */
@CrossOrigin
/* para evitar problemas de CORS*/

public class DetailController {

    @Autowired
    DetailRepository dr;

    @GetMapping(value="/details")
    List<Detail> getAllDetails(){
        return dr.findAll();
    }
    
}
