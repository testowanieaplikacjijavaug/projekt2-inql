package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Client;

import java.util.Set;

public interface ClientService {
    Set<Client> getClients();
    Client findById(Long l) throws NotFoundException;
    Client findByEmail(String email) throws NotFoundException;
    Set<Client> findClientsByName(String name);
    Set<Client> findClientsBySurname(String surname);
    Client save(Client client);
    void deleteById(Long idToDelete);


}
