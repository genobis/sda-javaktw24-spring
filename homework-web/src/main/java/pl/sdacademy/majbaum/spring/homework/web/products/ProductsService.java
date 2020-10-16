package pl.sdacademy.majbaum.spring.homework.web.products;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public List<Product> getProducts() {
        return products;
    }

    public synchronized Optional<Product> getProduct(int id) {
        if (id < 0 || id >= products.size()) {
            return Optional.empty();
        }

        return Optional.of(products.get(id));
    }

    public synchronized void addProduct(Product product) {
        int index = products.size();
        product.setId(index);
        products.add(product);
    }
}
