package com.nazir.reactive.repository;

import com.nazir.reactive.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByCategory(String category);

    Flux<Product> findByNameContainingIgnoreCase(String name);

    Flux<Product> findByPriceLessThanEqual(Double price);
}