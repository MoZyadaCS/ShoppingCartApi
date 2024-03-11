package com.eg.swa.ntier.shopping.controller;

import java.util.List;

import com.eg.swa.ntier.shopping.dto.PageableResponseDto;
import com.eg.swa.ntier.shopping.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eg.swa.ntier.shopping.model.Product;
import com.eg.swa.ntier.shopping.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<PageableResponseDto<Product>> getAllProducts(
            @RequestParam(name = "page",required = false) int page,
            @RequestParam(name = "count", required = false) int count) {
        PageableResponseDto<Product> responseDto = productService.getAllProducts(page,count);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // need to have a function to search for product by name
    @GetMapping("products/search")
    public ResponseEntity<List<Product>> searchByName(@RequestParam (name = "name" , required = true) String name ){
        List<Product> products = productService.searchByName(name);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    // need to get product by ID
    @GetMapping("products/{productId}")
    public ResponseEntity<Product> getById(@PathVariable(value = "productId") Long productId){
        Product product = productService.getById(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    // Need have a function to create product
    @PostMapping("admin/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid ProductDto productDto){
        productService.createProduct(productDto);
    }

    // Need have a function to update product
    @PutMapping("admin/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable Long productId ,@RequestBody @Valid ProductDto productDto){
        productService.updateProduct(productId,productDto);
    }
    
   // Need have a function to delete product
    @DeleteMapping("admin/products{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }
}

