package com.api.user.registration.repository;

import com.api.user.registration.entity.Contact;
import com.api.user.registration.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    List<Contact> findAllContactsByUsername(String username);

    Contact findContactsByUsernameAndContactType(String username);
}
