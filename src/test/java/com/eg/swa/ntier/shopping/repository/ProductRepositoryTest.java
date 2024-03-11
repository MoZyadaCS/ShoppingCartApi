package com.eg.swa.ntier.shopping.repository;

import com.eg.swa.ntier.shopping.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void shouldSearchByNameIgnoringCase(){
        // given
        Product p1 = new Product();
        p1.setName("iPhone 13 Pro");
        p1.setPrice(BigDecimal.valueOf(1299.00));
        p1.setQuantity(10);
        underTest.save(p1);

        Product p2 = new Product();
        p2.setName("Sony PlayStation 5");
        p2.setPrice(BigDecimal.valueOf(499.99));
        p2.setQuantity(15);
        underTest.save(p2);

        // when
        List<Product> productList = underTest.findByNameContainingIgnoreCase("iphone");

        // then
        assertAll(
                () -> assertThat(productList).isNotEmpty(),
                () -> assertThat(productList.size()).isEqualTo(1),
                () -> assertThat(productList.get(0).getName()).containsIgnoringCase("iphone")
        );
    }

    @Test
    public void shouldGetProductById(){
        // given
        Product p1 = new Product();
        p1.setName("iPhone 13 Pro");
        p1.setPrice(BigDecimal.valueOf(1299.00));
        p1.setQuantity(10);
        underTest.save(p1);

        Product p2 = new Product();
        p2.setName("Sony PlayStation 5");
        p2.setPrice(BigDecimal.valueOf(499.99));
        p2.setQuantity(15);
        underTest.save(p2);

        // when
        Optional<Product> product = underTest.findById(1L);
        // then
        assertAll(
                () -> assertThat(product).isPresent(),
                () -> assertThat(product.get().getName()).isEqualTo("iPhone 13 Pro")
        );
    }

    @Test
    public void shouldSaveProduct(){
        // given
        Product product = new Product();
        product.setName("iPhone 13");
        product.setPrice(BigDecimal.valueOf(1999.99));
        product.setQuantity(10);

        // when
        product = underTest.save(product);
        // then
        Product finalProduct = product;
        assertAll(
                () -> assertThat(finalProduct).isNotNull(),
                () -> assertThat(finalProduct.getId()).isNotNull()
        );
    }

    @Test
    public void shouldMarkProductAsDeleted() throws InterruptedException {
        // given
        Long productId = 1l;
        Product product = new Product();
        product.setId(productId);
        product.setName("iPhone 13");
        product.setQuantity(10);
        product.setPrice(BigDecimal.valueOf(1999));
        product.setDeleted(false);

        underTest.save(product);

        // when
        underTest.setProductAsDeleted(productId);

        // clear the entity manager cache after updating the product
        // to get the most updated data from the database
        entityManager.clear();
        Optional<Product> savedProduct = underTest.findById(productId);
        // then
        assertAll(
                () -> assertThat(savedProduct).isPresent(),
                () -> assertThat(savedProduct.get().getId()).isEqualTo(productId),
                () -> assertTrue(savedProduct.get().getDeleted())
        );
    }
}