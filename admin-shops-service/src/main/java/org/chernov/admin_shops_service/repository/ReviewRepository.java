package org.chernov.admin_shops_service.repository;

import org.chernov.admin_shops_service.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
}
