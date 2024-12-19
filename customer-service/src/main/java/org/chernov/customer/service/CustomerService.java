package org.chernov.customer.service;


import lombok.RequiredArgsConstructor;
import org.chernov.customer.entity.Customer;
import org.chernov.customer.repository.CustomerRepository;
import org.chernov.customer.request.RegisterCustomerRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void registerCustomer(RegisterCustomerRequest registerCustomerRequest) {

        Customer customer = Customer.builder()
                .firstName(registerCustomerRequest.firstName())
                .lastName(registerCustomerRequest.lastName())
                .email(registerCustomerRequest.email())
                .build();

        customerRepository.save(customer);
    }
}
