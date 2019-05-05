package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Client;

import java.util.Set;

public interface ClientService {
    Set<Client> getClients();
    Client findById(Long l) throws NotFoundException;
    Iterable<Client> findAll(Iterable<Long> clients_id);
    Client findByEmail(String email) throws NotFoundException;
    Set<Client> findClientsByName(String name);
    Set<Client> findClientsBySurname(String surname);
    Set<Client> findByNameContaining(String keyword);
    Set<Client> findBySurnameContaining(String keyword);
    Set<Client> findByEmailContaining(String keyword);
    Client save(Client client);
    Iterable<Client> saveAll(Iterable<Client> clients);
    void deleteById(Long idToDelete);


}
