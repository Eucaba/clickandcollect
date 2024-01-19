package practica2023.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {

		System.setProperty("spring.profiles.active", "dev");
		//activa el perfil de dev cuando ejecutas en local, y por tanto puedes acceder a las propiedades privadas

		SpringApplication.run(EcommerceApplication.class, args);
	}

}
