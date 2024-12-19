package org.chernov.fraud.service;


import lombok.RequiredArgsConstructor;
import org.chernov.fraud.entity.FraudCheckHistory;
import org.chernov.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository checkHistoryRepository;
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Long customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
