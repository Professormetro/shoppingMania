package org.chernov.customer.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chernov.customer.request.RegisterCustomerRequest;
import org.chernov.customer.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody RegisterCustomerRequest registerCustomerRequest){
        log.info("Registering new customer {}", registerCustomerRequest);
        customerService.registerCustomer(registerCustomerRequest);

    }

}
