package com.api.user.registration.controller;

import com.api.user.registration.entity.Contact;
import com.api.user.registration.entity.Notifications;
import com.api.user.registration.entity.Users;
import com.api.user.registration.repository.ContactRepository;
import com.api.user.registration.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserRegistrationController {

    private final UsersRepository usersRepository;
    private final ContactRepository contactRepository;

    public UserRegistrationController(UsersRepository usersRepository, ContactRepository contactRepository) {
        this.usersRepository = usersRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping(
            value = "/check",
            produces = "application/json"
    )
    public ResponseEntity<Boolean> check() {
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping(
            value = "/register",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<UUID> register(@RequestBody Users user) {
        user.setUuid(UUID.randomUUID());
        Users savedUser = this.usersRepository.saveAndFlush(user);
        return Optional.of(savedUser).map(Users::getUuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping(
            value = "/login",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<Boolean> login(@RequestBody Users user) {
        Optional<Users> userResult = this.usersRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userResult.map(u -> ResponseEntity.ok(Boolean.TRUE)).orElse(ResponseEntity.ok(Boolean.FALSE));
    }

    @DeleteMapping(
            value = "/deregister/{userid}",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<Boolean> deregister(@PathVariable String userId) {
        this.usersRepository.deleteById(userId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping(
            produces = "application/json"
    )
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> allUsers = this.usersRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping(
            value = "/{uuid}",
            produces = "application/json"
    )
    public ResponseEntity<Users> getUser(@PathVariable UUID uuid) {
        Optional<Users> userResult = this.usersRepository.findUserById(uuid);
        return userResult
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(
            value = "/{uuid}/contacts",
            produces = "application/json"
    )
    public ResponseEntity<List<Contact>> getUserContacts(@PathVariable UUID uuid) {
        List<Contact> contacts = this.contactRepository.findAllUserContactsByUuid(uuid);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping(
            value = "/{username}/contacts/{contactType}",
            produces = "application/json"
    )
    public ResponseEntity<Contact> getUserContactsByType(@PathVariable String username, @PathVariable String contactType) {
        Contact contactResult = this.contactRepository.findContactsByUsernameAndContactType(username, contactType);
        return ResponseEntity.ok(contactResult);
    }

    @PostMapping(
            value = "/notify",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<Notifications> notify(@RequestBody Notifications notifications) {
        return ResponseEntity.ok(Notifications.builder().build());
    }
}
