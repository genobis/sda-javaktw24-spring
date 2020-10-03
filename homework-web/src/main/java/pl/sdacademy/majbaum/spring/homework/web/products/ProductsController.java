package pl.sdacademy.majbaum.spring.homework.web.products;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductsController {

    //[GET] /products
    //Widok listy produktów (nazwy jako linki prowadzące na stronę produktu) + link do formularza
    @GetMapping
    ModelAndView getProducts() {
        throw new UnsupportedOperationException("TODO");
    }

    //[GET] /products/{id}
    //Widok szczegółowy produktu
    @GetMapping("/{id}")
    ModelAndView getProduct(@PathVariable int id) {
        //Zaimplementować warstwę serwisową zwracającą produkt wg id. Id niech będzie po prostu indeksem w List<Product>
        throw new UnsupportedOperationException("TODO");
    }

    //[POST] /products
    //Dodawanie produktu, po wykonaniu - pokazanie listy jak wyżej
    //Obsługa przyjmowanych danych dowolna, może być z @RequestParam i mapą bądź pojedyncze wartości
    @PostMapping
    ModelAndView addProduct(@ModelAttribute Product product) {
        throw new UnsupportedOperationException("TODO");
    }

    //[POST] /products/add
    //Formularz dodawania produktu. Uwaga, model może nie być potrzebny - wtedy obsłużyć inaczej
    @GetMapping("/add")
    ModelAndView getProductForm() {
        throw new UnsupportedOperationException("TODO");
    }
}
