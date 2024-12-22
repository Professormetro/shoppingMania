package org.chernov.authservice.repository;

import org.chernov.authservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

   AppUser findByEmail(String email);
   AppUser findByPhone(String phone);

}
