package com.tecnoshop.deleteProduct.service;

import com.tecnoshop.deleteProduct.model.Product;
import com.tecnoshop.deleteProduct.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public void delete(Long id) {
        Product p = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado o ya eliminado."));
        p.setDeletedAt(LocalDateTime.now());
        repo.save(p);
    }
}
