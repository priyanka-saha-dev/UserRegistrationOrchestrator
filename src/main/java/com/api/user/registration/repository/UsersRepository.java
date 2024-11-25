package com.api.user.registration.repository;

import com.api.user.registration.entity.Contact;
import com.api.user.registration.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    @Query("SELECT u FROM Users u WHERE u.uuid = :id")
    Optional<Users> findUserById(@Param("id") UUID id); // Correctly typed

    Optional<Users> findUserByUsernameAndPassword(String username, String password);

}
