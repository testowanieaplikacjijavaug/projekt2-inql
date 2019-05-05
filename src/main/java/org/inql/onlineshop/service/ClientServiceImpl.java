package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Client;
import org.inql.onlineshop.repository.ClientRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Client> getClients() {
        Set<Client> clientSet = new HashSet<>();
        clientRepository.findAll().iterator().forEachRemaining(clientSet::add);
        return clientSet;
    }

    @Override
    public Client findById(Long l) throws NotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(l);
        return clientOptional.orElseThrow(() -> new NotFoundException("Client not found"));
    }

    @Override
    public Iterable<Client> findAll(Iterable<Long> clients_id) {
        return clientRepository.findAllById(clients_id);
    }

    @Override
    public Client findByEmail(String email) throws NotFoundException {
        Optional<Client> clientOptional = clientRepository.findClientByEmail(email);
        return clientOptional.orElseThrow(() -> new NotFoundException("Client not found"));

    }

    @Override
    public Set<Client> findClientsByName(String name) {
        Set<Client> clientSet = new HashSet<>();
        clientRepository.findClientsByName(name).iterator().forEachRemaining(clientSet::add);
        return clientSet;
    }

    @Override
    public Set<Client> findClientsBySurname(String surname) {
        Set<Client> clientSet = new HashSet<>();
        clientRepository.findClientsBySurname(surname).iterator().forEachRemaining(clientSet::add);
        return clientSet;    }

    @Override
    public Set<Client> findByNameContaining(String keyword) {
        Set<Client> clientSet = new HashSet<>();
        clientRepository.findClientsByNameContaining(keyword).iterator().forEachRemaining(clientSet::add);
        return clientSet;
    }

    @Override
    public Set<Client> findBySurnameContaining(String keyword) {
        Set<Client> clientSet = new HashSet<>();
        clientRepository.findClientsBySurnameContaining(keyword).iterator().forEachRemaining(clientSet::add);
        return clientSet;
    }

    @Override
    public Set<Client> findByEmailContaining(String keyword) {
        Set<Client> clientSet = new HashSet<>();
        clientRepository.findClientsByEmailContaining(keyword).iterator().forEachRemaining(clientSet::add);
        return clientSet;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Iterable<Client> saveAll(Iterable<Client> clients) {
        return clientRepository.saveAll(clients);
    }

    @Override
    public void deleteById(Long idToDelete) {
        clientRepository.deleteById(idToDelete);
    }
}
