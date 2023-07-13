package dev.guilhermepisco.msscbrewery.web.service;

import dev.guilhermepisco.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return new CustomerDto(UUID.randomUUID(), "John");
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return new CustomerDto(UUID.randomUUID(), "Bob");
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.info("Updating Customer ...");
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        log.info("Deleting Customer ...");
    }
}
