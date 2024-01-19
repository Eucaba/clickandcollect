document.addEventListener("DOMContentLoaded", function () {

    const newOrderBttn = document.getElementById("postOrder")
    const newBasketBttn = document.getElementById("postBasket")
    const updateBasketBttn = document.getElementById("updateBasket")

    newOrderBttn.addEventListener('click', function () {
        //això es faria des de JS, no?
        let newOrder = {
            "order_id": null,
            "status": "pedido confirmado", //no accepta null, revissar si interessa
            "customer_name": "ejemplo",
            "customer_email": "ejemplo@onekru.com",
            "customer_telephone": "646323818",
            "amount": 7.0,
            "date": "2023-10-31T23:00:00.000+00:00",
            "time": "09:30:00"
        }  //cada cop que actualitzes, es grava un altre cop (això es por manejar)

        //hago un fetch ('url' del endpoint, info del post incluyendo el body que se requiere)
        fetch('http://localhost:8080/orders', {
            method: "POST",
            mode: "cors",
            //cache: "no-cache",
            //credentials: "same-origin",
            headers: {
                "Content-Type": "application/json",
            },
            redirect: "follow",
            //referrerPolicy: "no-referrer"
            body: JSON.stringify(newOrder),
            //body data type must match "Content-Type" header + stringify de JSON object a JSON string
        })
            .then(res => res.json()) //nos devuelve la respuesta en JSON object (ya parseado)
            .then(newOrder => {
                console.log(newOrder)
            })
    })

    newBasketBttn.addEventListener('click', function () {
        let newBasket = {
            "basket_id": null,
            "status": "ejemplo",
        }

        fetch('http://localhost:8080/baskets', {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
            },
            redirect: "follow",
            body: JSON.stringify(newBasket),
        })
            .then(res => res.json())
            .then(newBasket => {
                console.log(newBasket)
            })// Supongamos que ya tienes un objeto 'updatedOrder' con la información actualizada.

        updateBasketBttn.addEventListener('click', function () {
            let updateBasket = {
                "basket_id": 3,
                "status": "ejemplo",
            }

            fetch('http://localhost:8080/baskets/' + 3, {
                method: "PUT",
                mode: "cors",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(updateBasket),
            })
                .then(res => res.json())
                .then(updatedBasket => {
                    console.log(updatedBasket);
                })
        })
    })
})