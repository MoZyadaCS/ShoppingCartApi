package com.eg.swa.ntier.shopping.repository;

import com.eg.swa.ntier.shopping.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;



    @Test
    public void shouldCreateCustomerSuccessfully(){
        // given
        Customer customer = new Customer();
        customer.setName("Mostafa");
        customer.setEmail("mostafa@email.com");
        customer.setAddress("Cairo");
        customer.setPassword("123456");

        // when
        customer = underTest.save(customer);

        // then
        Customer finalCustomer = customer;
        assertAll(
                () -> assertThat(finalCustomer).isNotNull(),
                () -> assertThat(finalCustomer.getId()).isNotNull(),
                () -> assertThat(finalCustomer.getId()).isPositive()

        );

    }

}