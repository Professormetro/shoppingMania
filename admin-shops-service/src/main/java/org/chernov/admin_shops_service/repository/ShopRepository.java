package org.chernov.admin_shops_service.repository;

import org.chernov.admin_shops_service.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
