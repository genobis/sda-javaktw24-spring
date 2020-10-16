package pl.sdacademy.majbaum.spring.homework.web.products;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    //[GET] /products
    //Widok listy produktów (nazwy jako linki prowadzące na stronę produktu) + link do formularza
    @GetMapping
    ModelAndView getProducts() {
        final ModelAndView mav = new ModelAndView()
                .addObject("title", "Lista produktów")
                .addObject("products", productsService.getProducts());
        mav.setViewName("products");
        return mav;
    }

    //[GET] /products/{id}
    //Widok szczegółowy produktu
    @GetMapping("/{id}")
    ModelAndView getProduct(@PathVariable int id) {
        //Zaimplementować warstwę serwisową zwracającą produkt wg id. Id niech będzie po prostu indeksem w List<Product>
        final Product product = productsService.getProduct(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        final ModelAndView mav = new ModelAndView()
            .addObject("title", product.getName())
            .addObject("product", product);
        mav.setViewName("product-details");
        return mav;
    }

    //[POST] /products
    //Dodawanie produktu, po wykonaniu - pokazanie listy jak wyżej
    //Obsługa przyjmowanych danych dowolna, może być z @RequestParam i mapą bądź pojedyncze wartości
    @PostMapping
    ModelAndView addProduct(@ModelAttribute Product product) {
        productsService.addProduct(product);
        return getProducts();
    }

    //[POST] /products/add
    //Formularz dodawania produktu. Uwaga, model może nie być potrzebny - wtedy obsłużyć inaczej
    @GetMapping("/add")
    ModelAndView getProductForm() {
        final ModelAndView mav = new ModelAndView().addObject("title", "Dodawanie produktu");
        mav.setViewName("products-add");
        return mav;
    }
}
