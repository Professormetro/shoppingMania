package org.chernov.fraud.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chernov.fraud.response.FraudCheckResponse;
import org.chernov.fraud.service.FraudCheckService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @PostMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);

        return new FraudCheckResponse(isFraudulentCustomer);
    }


}
