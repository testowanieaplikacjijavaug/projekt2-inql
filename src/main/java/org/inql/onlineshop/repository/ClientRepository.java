package org.inql.onlineshop.repository;

import org.inql.onlineshop.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findClientByEmail(String email);
    Set<Client> findClientsByName(String name);
    Set<Client> findClientsBySurname(String surname);
}
