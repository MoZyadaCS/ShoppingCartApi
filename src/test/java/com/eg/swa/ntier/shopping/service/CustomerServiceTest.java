package com.eg.swa.ntier.shopping.service;

import com.eg.swa.ntier.shopping.dto.CustomerDto;
import com.eg.swa.ntier.shopping.model.Customer;
import com.eg.swa.ntier.shopping.model.Role;
import com.eg.swa.ntier.shopping.repository.CustomerRepository;
import com.eg.swa.ntier.shopping.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {



    @InjectMocks
    private CustomerService underTest;
    
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;


    @Test
    public void shouldCreateCustomerSuccessfully(){
        // given
        CustomerDto customerDto = new CustomerDto();
        when(roleRepository.findByName("CUSTOMER")).thenReturn(Optional.of(new Role()));
        // when
        underTest.createCustomer(customerDto);
        // then

        verify(customerRepository).save(any());
    }

    @Test
    public void shouldFailToCreateCustomerBecauseEmailExist(){
        // given
        String email = "mostafa@email.com";
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail(email);
        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(new Customer()));
        // when

        // then
        Assertions.assertThatThrownBy(
                () -> underTest.createCustomer(customerDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Customer Has Account Associated With This Email");
    }

}