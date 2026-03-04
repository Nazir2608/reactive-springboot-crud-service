package com.nazir.reactive.service;

import com.nazir.reactive.dto.ProductRequest;
import com.nazir.reactive.exception.ProductNotFoundException;
import com.nazir.reactive.model.Product;
import com.nazir.reactive.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Mono<Product> createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .createdAt(LocalDateTime.now())
                .build();

        return productRepository.save(product).doOnSuccess(p -> log.info("Created product: {}", p.getId()));
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll()
                .doOnComplete(() -> log.info("Fetched all products"));
    }

    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id).switchIfEmpty(Mono.error(new ProductNotFoundException(id)));
    }

    public Flux<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Flux<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Mono<Product> updateProduct(Long id, ProductRequest request) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
                .flatMap(existing -> {
                    existing.setName(request.getName());
                    existing.setDescription(request.getDescription());
                    existing.setPrice(request.getPrice());
                    existing.setQuantity(request.getQuantity());
                    existing.setCategory(request.getCategory());
                    existing.setUpdatedAt(LocalDateTime.now());
                    return productRepository.save(existing);
                })
                .doOnSuccess(p -> log.info("Updated product: {}", p.getId()));
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
                .flatMap(productRepository::delete)
                .doOnSuccess(v -> log.info("Deleted product: {}", id));
    }

    public Mono<Void> deleteAllProducts() {
        return productRepository.deleteAll();
    }
}