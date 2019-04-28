package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Client;
import org.inql.onlineshop.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceImplMockitoTest {

    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    void findClientByIdTest() throws NotFoundException {
        Client client = new Client();
        client.setId(1L);
        Optional<Client> clientOptional = Optional.of(client);

        when(clientRepository.findById(anyLong())).thenReturn(clientOptional);

        Client clientReturned = clientService.findById(1L);

        assertThat(clientReturned).isNotNull().isInstanceOf(Client.class).isEqualTo(client);
        verify(clientRepository, times(1)).findById(anyLong());
        verify(clientRepository, never()).findAll();
    }

    @Test
    void findClientByIdNotFoundTest() {
        Optional<Client> clientOptional = Optional.empty();

        when(clientRepository.findById(anyLong())).thenReturn(clientOptional);

        assertThatThrownBy(() -> clientService.findById(1L)).isInstanceOf(NotFoundException.class).hasMessage("Client not found");
    }

    @Test
    void findClientByEmailTest() throws NotFoundException {
        Client client = new Client();
        client.setEmail("example@example.com");
        Optional<Client> clientOptional = Optional.of(client);

        when(clientRepository.findClientByEmail(anyString())).thenReturn(clientOptional);

        Client clientReturned = clientService.findByEmail("example@example.com");

        assertThat(clientReturned).isNotNull().isInstanceOf(Client.class).isEqualTo(client);
        verify(clientRepository, times(1)).findClientByEmail(anyString());
        verify(clientRepository, never()).findAll();
    }

    @Test
    void findClientByEmailNotFoundTest() {
        Optional<Client> clientOptional = Optional.empty();

        when(clientRepository.findClientByEmail(anyString())).thenReturn(clientOptional);

        assertThatThrownBy(() -> clientService.findByEmail("example@example.com")).isInstanceOf(NotFoundException.class).hasMessage("Client not found");
    }

    @Test
    void findClientsByNameTest() {
        Client client = new Client();
        client.setName("Marcin");
        HashSet clientsData = new HashSet();
        clientsData.add(client);

        when(clientService.findClientsByName("Marcin")).thenReturn(clientsData);

        Set<Client> clients = clientService.findClientsByName("Marcin");

        assertThat(clients).isNotNull().hasSize(1).containsOnly(client).isInstanceOf(Set.class);
        verify(clientRepository, times(1)).findClientsByName("Marcin");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsBySurnameTest() {
        Client client = new Client();
        client.setSurname("Abacki");
        HashSet clientsData = new HashSet();
        clientsData.add(client);

        when(clientService.findClientsBySurname("Abacki")).thenReturn(clientsData);

        Set<Client> clients = clientService.findClientsBySurname("Abacki");

        assertThat(clients).isNotNull().hasSize(1).containsOnly(client).isInstanceOf(Set.class);
        verify(clientRepository, times(1)).findClientsBySurname("Abacki");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void getClientsTest() {
        Client client = new Client();
        HashSet clientsData = new HashSet();
        clientsData.add(client);

        when(clientService.getClients()).thenReturn(clientsData);

        Set<Client> clients = clientService.getClients();

        assertThat(clients).isNotNull().hasSize(1).containsOnly(client).isInstanceOf(Set.class);
        verify(clientRepository, times(1)).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void deleteByIdTest() {
        Long idToDelete = 2L;

        clientService.deleteById(idToDelete);

        verify(clientRepository, times(1)).deleteById(anyLong());
    }

    @AfterEach
    void tearDown() {
        clientService = null;
        clientRepository = null;
    }
}
