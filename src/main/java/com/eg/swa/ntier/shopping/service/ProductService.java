package com.eg.swa.ntier.shopping.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.eg.swa.ntier.shopping.dto.PageableResponseDto;
import com.eg.swa.ntier.shopping.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eg.swa.ntier.shopping.model.Product;
import com.eg.swa.ntier.shopping.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PageableResponseDto<Product> getAllProducts(int page, int count) {
        PageableResponseDto<Product> responseDto = new PageableResponseDto<>();
        responseDto.setCount(count);
        responseDto.setPage(page);
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page,count));
        responseDto.setElements(productPage.getContent());
        responseDto.setTotalPages(productPage.getTotalPages());
        return responseDto;
    }
    public List<Product> searchByName(String name) {
        return this.productRepository.findByNameContainingIgnoreCase(name);
    }

    public Product getById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        this.productRepository.save(product);
    }

    public void updateProduct(Long productId, ProductDto productDto) {
        Product product = getById(productId);
        product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        productRepository.setProductAsDeleted(productId);
    }
}
