package com.test.yazykov.services;

import com.test.yazykov.domain.Customer;
import com.test.yazykov.domain.IdType;
import com.test.yazykov.dto.CustomerDetails;
import com.test.yazykov.dto.SignIn;
import com.test.yazykov.dto.UpdateCustomer;
import com.test.yazykov.repositories.AccountRepo;
import com.test.yazykov.repositories.CustomerRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceRestTest {

    @InjectMocks
    private CustomerServiceRest customerServiceRest;
    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private AccountRepo accountRepo;

    @Test
    void register_ok() {
        //given
        var signIn = new SignIn("name", "111111111111", IdType.PASSPORT);
        var customer = new Customer(null, "name", "111111111111", IdType.PASSPORT, new HashSet<>());
        var customerS = new Customer(1L, "name", "111111111111", IdType.PASSPORT, new HashSet<>());
        //mock
        Mockito.when(customerRepo.save(customer)).thenReturn(customerS);
        //when
        CustomerDetails register = customerServiceRest.register(signIn);
        //then
        Assertions.assertEquals(register.idNumber(), signIn.idNumber());
    }

    @Test
    void update_throw() {
        //given
        var update = new UpdateCustomer(1L, "name", "444444444444", IdType.PASSPORT);
        //when
        Assertions.assertThrows(RuntimeException.class, () -> customerServiceRest.update(update, 5L));
    }

    //and so on
}