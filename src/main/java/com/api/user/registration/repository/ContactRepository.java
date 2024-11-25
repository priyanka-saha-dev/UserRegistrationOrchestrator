package com.api.user.registration.repository;

import com.api.user.registration.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query(value = "SELECT C.* FROM CONTACTS C JOIN USERS U ON C.USER_UUID = U.UUID WHERE U.USERNAME = :username AND C.TYPE = :contactType", nativeQuery = true)
    Contact findContactsByUsernameAndContactType(@Param("username") String username, @Param("contactType") String contactType);

    @Query(value = "SELECT * FROM CONTACTS C WHERE C.user_uuid = :id", nativeQuery = true)
    List<Contact> findAllUserContactsByUuid(@Param("id") UUID id);
}
