package org.chernov.fraud.repository;

import org.chernov.fraud.entity.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Long> {
}
