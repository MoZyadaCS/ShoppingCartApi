package com.eg.swa.ntier.shopping.service;

import com.eg.swa.ntier.shopping.dto.PageableResponseDto;
import com.eg.swa.ntier.shopping.dto.ProductDto;
import com.eg.swa.ntier.shopping.model.Product;
import com.eg.swa.ntier.shopping.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService underTest;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldGetAllProductsSuccessfully(){
        // given
        Page<Product> pageObject = Mockito.mock(Page.class);
        int page  = 0;
        int count = 2;
        when(productRepository.findAll(PageRequest.of(page,count))).thenReturn(pageObject);

        // when
        underTest.getAllProducts(page,count);

        // then
        verify(productRepository).findAll(PageRequest.of(page,count));
    }

    @Test
    public void shouldSearchProductsByName(){
        // given
        Product p1 = new Product();
        p1.setName("iPhone 13 Pro");
        p1.setPrice(BigDecimal.valueOf(1299.00));
        p1.setQuantity(10);

        String searchString = "iphone";
        when(productRepository.findByNameContainingIgnoreCase(searchString)).thenReturn(List.of(p1));
        // when
        List<Product> products = underTest.searchByName(searchString);

        // then

        assertAll(
                () -> assertThat(products).isNotNull(),
                () -> assertThat(products).isNotEmpty(),
                () -> assertThat(products.size()).isEqualTo(1),
                () -> assertThat(products.get(0).getName()).containsIgnoringCase(searchString)
        );
    }


    @Test
    public void shouldGetById(){
        // given
        Long id = 1L;
        Product p1 = new Product();
        p1.setId(id);
        p1.setName("iPhone 13 Pro");
        p1.setPrice(BigDecimal.valueOf(1299.00));
        p1.setQuantity(10);
        when(productRepository.findById(id)).thenReturn(Optional.of(p1));

        // when
        Product product = underTest.getById(id);
        // then

        assertAll(
                () -> assertThat(product).isNotNull(),
                () -> assertThat(product.getId()).isEqualTo(id)
        );
    }

    @Test
    public void shouldSaveProductSuccessfully(){
        // given
        String name = "iPhone 13";
        Double price = 1999.99;
        Integer quantity = 10;
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setQuantity(quantity);

        // when
        underTest.createProduct(productDto);

        // then
        verify(productRepository,times(1)).save(any());
    }

    @Test
    public void shouldUpdateProductDetails(){
        // given
        String name = "iPhone 13";
        Double price = 1999.99;
        Integer quantity = 10;
        Long id = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setQuantity(quantity);

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(BigDecimal.valueOf(price));
        product.setQuantity(quantity);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // when
        underTest.updateProduct(id,productDto);

        // then
        verify(productRepository,times(1)).save(any());
    }

}