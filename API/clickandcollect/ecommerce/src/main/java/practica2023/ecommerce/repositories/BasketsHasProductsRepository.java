package practica2023.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import practica2023.ecommerce.entities.BasketsHasProducts;
import practica2023.ecommerce.entities.BasketsHasProductsId;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface BasketsHasProductsRepository extends JpaRepository<BasketsHasProducts, BasketsHasProductsId> {

    @Transactional
    @Modifying //el método opera en la BBDD, no es únicamente de lectura
    @Query("DELETE FROM BasketsHasProducts bhp WHERE bhp.id.basket_id = :mBasketId")
    void deleteBasketsHasProducstByBasketId(@Param("mBasketId") int mBasketId);

    /*Una transacción agrupa un conjunto de operaciones: las operaciones se realizan todas o ninguna.
    Si alguna operación falla, se revierten todas las operaciones anteriores*/

    /* WHERE bhp.id.basket_id = :mBasketId establece condición eliminación.
    El valor de la columna basket_id en la clave primaria compuesta (id) de la entidad BasketsHasProducts
    debe coincidir con el parámetro :mBasketId. */

    /*la consulta Java Persistence Query Languaje indica que se deben eliminar las filas de la entidad BasketsHasProducts
    donde el valor de la columna basket_id coincida con el valor proporcionado en el parámetro mBasketId*/

    
}
