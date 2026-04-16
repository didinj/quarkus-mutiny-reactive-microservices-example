package com.djamware;

import java.time.Duration;
import java.util.List;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService {
    private final List<Product> products = List.of(
            new Product(1L, "Laptop", 1200.0),
            new Product(2L, "Mouse", 25.0),
            new Product(3L, "Keyboard", 75.0));

    public Uni<List<Product>> getAllProducts() {
        return Uni.createFrom().item(products);
    }

    public Uni<Product> getProductById(Long id) {
        return Uni.createFrom()
                .item(products.stream()
                        .filter(p -> p.id.equals(id))
                        .findFirst()
                        .orElse(null));
    }

    public Multi<Product> streamProducts() {
        return Multi.createFrom()
                .iterable(products)
                .onItem().call(product -> Uni.createFrom()
                        .nullItem()
                        .onItem().delayIt().by(Duration.ofSeconds(1)));
    }

    public Uni<Product> getDiscountedProduct(Long id) {
        return getProductById(id)
                .onItem().transform(product -> {
                    product.price = product.price * 0.9;
                    return product;
                });
    }
}
