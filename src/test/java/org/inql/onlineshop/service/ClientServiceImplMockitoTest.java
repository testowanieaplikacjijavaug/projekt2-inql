package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Client;
import org.inql.onlineshop.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
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

        when(clientRepository.findById(1L)).thenReturn(clientOptional);

        Client clientReturned = clientService.findById(1L);

        assertThat(clientReturned).isNotNull().isInstanceOf(Client.class).isEqualTo(client);
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, never()).findAll();
    }

    @Test
    void findClientByIdNotFoundTest() {
        Optional<Client> clientOptional = Optional.empty();

        when(clientRepository.findById(1L)).thenReturn(clientOptional);

        assertThatThrownBy(() -> clientService.findById(1L)).isInstanceOf(NotFoundException.class).hasMessage("Client not found");
    }

    @Test
    void findClientByIdNullInputTest() {
        when(clientRepository.findById(null)).thenThrow(new IllegalArgumentException("Null id not allowed"));

        assertThatThrownBy(() -> clientService.findById(null)).isInstanceOf(IllegalArgumentException.class).hasMessage("Null id not allowed");
    }

    @Test
    void findClientByEmailTest() throws NotFoundException {
        Client client = new Client();
        client.setEmail("example@example.com");
        Optional<Client> clientOptional = Optional.of(client);

        when(clientRepository.findClientByEmail("example@example.com")).thenReturn(clientOptional);

        Client clientReturned = clientService.findByEmail("example@example.com");

        assertThat(clientReturned).isNotNull().isInstanceOf(Client.class).isEqualTo(client);
        verify(clientRepository, times(1)).findClientByEmail("example@example.com");
        verify(clientRepository, never()).findAll();
    }

    @Test
    void findClientByEmailNotFoundTest() {
        Optional<Client> clientOptional = Optional.empty();

        when(clientRepository.findClientByEmail("example@example.com")).thenReturn(clientOptional);

        assertThatThrownBy(() -> clientService.findByEmail("example@example.com")).isInstanceOf(NotFoundException.class).hasMessage("Client not found");
    }

    @Test
    void findClientByEmailNullInputTest() {
        String email = null;

        when(clientRepository.findClientByEmail(email)).thenThrow(new IllegalArgumentException("Null email not allowed"));

        assertThatThrownBy(() -> clientService.findByEmail(email)).isInstanceOf(IllegalArgumentException.class).hasMessage("Null email not allowed");
        verify(clientRepository,times(1)).findClientByEmail(null);
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientByEmailEmptyInputTest() {
        Optional<Client> clientOptional = Optional.empty();

        when(clientRepository.findClientByEmail("")).thenReturn(clientOptional);

        assertThatThrownBy(() -> clientService.findByEmail("")).isInstanceOf(NotFoundException.class).hasMessage("Client not found");
        verify(clientRepository,times(1)).findClientByEmail("");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByEmailContainingTest() {
        Client client = new Client();
        Client secondClient = new Client();
        Client thirdClient = new Client();
        client.setEmail("first@domain.com");
        secondClient.setEmail("second@domain.com");
        thirdClient.setEmail("third@inf.ug.edu.pl");
        HashSet<Client> clientsData = new HashSet<>(Arrays.asList(client,secondClient,thirdClient));

        when(clientRepository.findClientsByEmailContaining("domain")).thenReturn(new HashSet<>(Arrays.asList(client,secondClient)));

        Set<Client> clientsReturned = clientService.findByEmailContaining("domain");

        assertThat(clientsReturned).isNotNull().isNotEmpty().isNotEqualTo(clientsData).isInstanceOf(Set.class).hasOnlyElementsOfType(Client.class).hasSize(2).containsExactlyInAnyOrder(client,secondClient);
        verify(clientRepository,times(1)).findClientsByEmailContaining("domain");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByEmailContainingEmptyResultTest() {
        Client client = new Client();
        Client secondClient = new Client();
        Client thirdClient = new Client();
        client.setEmail("first@domain.com");
        secondClient.setEmail("second@domain.com");
        thirdClient.setEmail("third@inf.ug.edu.pl");
        HashSet<Client> clientsData = new HashSet<>(Arrays.asList(client,secondClient,thirdClient));

        when(clientRepository.findClientsByEmailContaining("google")).thenReturn(new HashSet<>());

        Set<Client> clientsReturned = clientService.findByEmailContaining("google");

        assertThat(clientsReturned).isNotNull().isEmpty();
        verify(clientRepository,times(1)).findClientsByEmailContaining("google");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByEmailContainingNullInputTest() {
        when(clientRepository.findClientsByEmailContaining(null)).thenThrow(new NotFoundException("Null keyword not allowed"));

        assertThatThrownBy(() -> clientService.findByEmailContaining(null)).isInstanceOf(NotFoundException.class).hasMessage("Null keyword not allowed");
        verify(clientRepository,times(1)).findClientsByEmailContaining(null);
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByEmailContainingEmptyInputTest() {
        when(clientRepository.findClientsByEmailContaining("")).thenReturn(new HashSet<>());

        Set<Client> clientsReturned = clientService.findByEmailContaining("");

        assertThat(clientsReturned).isNotNull().isEmpty();
        verify(clientRepository,times(1)).findClientsByEmailContaining("");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByNameTest() {
        Client client = new Client();
        client.setName("Marcin");
        HashSet clientsData = new HashSet();
        clientsData.add(client);

        when(clientRepository.findClientsByName("Marcin")).thenReturn(clientsData);

        Set<Client> clients = clientService.findClientsByName("Marcin");

        assertThat(clients).isNotNull().hasSize(1).containsOnly(client).isInstanceOf(Set.class);
        verify(clientRepository, times(1)).findClientsByName("Marcin");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(1L);
    }

    @Test
    void findClientsByNameEmptyTest() {
        Client client = new Client();
        client.setName("Marcin");
        HashSet clientsData = new HashSet();
        clientsData.add(client);

        when(clientRepository.findClientsByName("Tomasz")).thenReturn(new HashSet<>());

        Set<Client> clients = clientService.findClientsByName("Tomasz");

        assertThat(clients).isEmpty();
        verify(clientRepository, times(1)).findClientsByName("Tomasz");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(1L);
    }

    @Test
    void findClientByNameNullInputTest() {
        String name = null;

        when(clientRepository.findClientsByName(name)).thenThrow(new IllegalArgumentException("Null email not allowed"));

        assertThatThrownBy(() -> clientService.findClientsByName(name)).isInstanceOf(IllegalArgumentException.class).hasMessage("Null email not allowed");
        verify(clientRepository,times(1)).findClientsByName(null);
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientByNameEmptyInputTest() {
        when(clientRepository.findClientsByName("")).thenReturn(new HashSet<>());

        Set<Client> clientsReturned = clientService.findClientsByName("");

        assertThat(clientsReturned).isNotNull().isInstanceOf(Set.class).isEmpty();
        verify(clientRepository,times(1)).findClientsByName("");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }


    @Test
    void findClientsBySurnameTest() {
        Client client = new Client();
        client.setSurname("Abacki");
        HashSet clientsData = new HashSet();
        clientsData.add(client);

        when(clientRepository.findClientsBySurname("Abacki")).thenReturn(clientsData);

        Set<Client> clients = clientService.findClientsBySurname("Abacki");

        assertThat(clients).isNotNull().hasSize(1).containsOnly(client).isInstanceOf(Set.class);
        verify(clientRepository, times(1)).findClientsBySurname("Abacki");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(1L);
    }

    @Test
    void findClientsBySurnameEmptyTest() {
        Client client = new Client();
        client.setSurname("Abacki");
        HashSet clientsData = new HashSet();
        clientsData.add(client);

        when(clientRepository.findClientsBySurname("Babacki")).thenReturn(new HashSet<>());

        Set<Client> clients = clientService.findClientsBySurname("Babacki");

        assertThat(clients).isEmpty();
        verify(clientRepository, times(1)).findClientsBySurname("Babacki");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(1L);
    }

    @Test
    void findClientBySurnameNullInputTest() {
        String surname = null;

        when(clientRepository.findClientsBySurname(surname)).thenThrow(new IllegalArgumentException("Null surname not allowed"));

        assertThatThrownBy(() -> clientService.findClientsBySurname(surname)).isInstanceOf(IllegalArgumentException.class).hasMessage("Null surname not allowed");
        verify(clientRepository,times(1)).findClientsBySurname(null);
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientBySurnameEmptyInputTest() {
        when(clientRepository.findClientsBySurname("")).thenReturn(new HashSet<>());

        Set<Client> clientsReturned = clientService.findClientsBySurname("");

        assertThat(clientsReturned).isNotNull().isInstanceOf(Set.class).isEmpty();
        verify(clientRepository,times(1)).findClientsBySurname("");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByNameContainingTest() {
        Client client = new Client();
        Client secondClient = new Client();
        Client thirdClient = new Client();
        client.setName("Adam");
        secondClient.setName("Adrianna");
        thirdClient.setName("Piotr");
        HashSet<Client> clientsData = new HashSet<>(Arrays.asList(client, secondClient, thirdClient));

        when(clientRepository.findClientsByNameContaining("Ad")).thenReturn(new HashSet(Arrays.asList(client,secondClient)));

        Set<Client> clientsReturned = clientService.findByNameContaining("Ad");

        assertThat(clientsReturned).isNotNull().isNotEmpty().hasSize(2).isInstanceOf(Set.class).hasOnlyElementsOfType(Client.class).containsExactlyInAnyOrder(client,secondClient);
        verify(clientRepository, times(1)).findClientsByNameContaining("Ad");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByNameContainingEmptyResultTest() {
        Client client = new Client();
        Client secondClient = new Client();
        Client thirdClient = new Client();
        client.setName("Adam");
        secondClient.setName("Adrianna");
        thirdClient.setName("Piotr");
        HashSet<Client> clientsData = new HashSet<>(Arrays.asList(client, secondClient, thirdClient));

        when(clientRepository.findClientsByNameContaining("Zu")).thenReturn(new HashSet<>());

        Set<Client> clientsReturned = clientService.findByNameContaining("Zu");

        assertThat(clientsReturned).isEmpty();
        verify(clientRepository, times(1)).findClientsByNameContaining("Zu");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByNameContainingNullInputTest() {
        when(clientRepository.findClientsByNameContaining(null)).thenThrow(new NotFoundException("Null keyword not allowed"));

        assertThatThrownBy(() -> clientService.findByNameContaining(null)).isInstanceOf(NotFoundException.class).hasMessage("Null keyword not allowed");
        verify(clientRepository,times(1)).findClientsByNameContaining(null);
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsByNameContainingEmptyInputTest() {
        when(clientRepository.findClientsByNameContaining("")).thenReturn(new HashSet<>());

        Set<Client> clientsReturned = clientService.findByNameContaining("");

        assertThat(clientsReturned).isNotNull().isInstanceOf(Set.class).isEmpty();
        verify(clientRepository,times(1)).findClientsByNameContaining("");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsBySurnameContainingTest() {
        Client client = new Client();
        Client secondClient = new Client();
        Client thirdClient = new Client();
        client.setSurname("Nowak");
        secondClient.setSurname("Babacki");
        thirdClient.setSurname("Cabacki");
        HashSet<Client> clientsData = new HashSet<>(Arrays.asList(client, secondClient, thirdClient));

        when(clientRepository.findClientsBySurnameContaining("ba")).thenReturn(new HashSet(Arrays.asList(secondClient,thirdClient)));

        Set<Client> clientsReturned = clientService.findBySurnameContaining("ba");

        assertThat(clientsReturned).isNotNull().isNotEmpty().hasSize(2).isInstanceOf(Set.class).hasOnlyElementsOfType(Client.class).containsExactlyInAnyOrder(secondClient,thirdClient);
        verify(clientRepository, times(1)).findClientsByNameContaining("ba");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsBySurnameContainingEmptyResultTest() {
        Client client = new Client();
        Client secondClient = new Client();
        Client thirdClient = new Client();
        client.setSurname("Nowak");
        secondClient.setSurname("Babacki");
        thirdClient.setSurname("Cabacki");
        HashSet<Client> clientsData = new HashSet<>(Arrays.asList(client, secondClient, thirdClient));

        when(clientRepository.findClientsBySurnameContaining("Zu")).thenReturn(new HashSet<>());

        Set<Client> clientsReturned = clientService.findBySurnameContaining("Zu");

        assertThat(clientsReturned).isEmpty();
        verify(clientRepository, times(1)).findClientsByNameContaining("Zu");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsBySurnameContainingNullInputTest() {
        when(clientRepository.findClientsBySurnameContaining(null)).thenThrow(new NotFoundException("Null keyword not allowed"));

        assertThatThrownBy(() -> clientService.findBySurnameContaining(null)).isInstanceOf(NotFoundException.class).hasMessage("Null keyword not allowed");
        verify(clientRepository,times(1)).findClientsBySurnameContaining(null);
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void findClientsBySurnameContainingEmptyInputTest() {
        when(clientRepository.findClientsBySurnameContaining("")).thenReturn(new HashSet<>());

        Set<Client> clientsReturned = clientService.findBySurnameContaining("");

        assertThat(clientsReturned).isNotNull().isInstanceOf(Set.class).isEmpty();
        verify(clientRepository,times(1)).findClientsBySurnameContaining("");
        verify(clientRepository, never()).findAll();
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void getClientsTest() {
        Client client = new Client();
        HashSet clientsData = new HashSet();
        clientsData.add(client);

        when(clientRepository.findAll()).thenReturn(clientsData);

        Set<Client> clients = clientService.getClients();

        assertThat(clients).isNotNull().hasSize(1).containsOnly(client).isInstanceOf(Set.class);
        verify(clientRepository, times(1)).findAll();
        verify(clientRepository, never()).findById(1L);
    }

    @Test
    void deleteByIdTest() {
        Long idToDelete = 2L;

        clientService.deleteById(idToDelete);

        verify(clientRepository, times(1)).deleteById(2L);
    }

    @Test
    void deleteByIdNullInputTest() {
        Long idToDelete = null;

        doThrow(new NotFoundException("Null id not allowed")).when(clientRepository).deleteById(null);

        assertThatThrownBy(() -> clientService.deleteById(idToDelete)).isInstanceOf(NotFoundException.class).hasMessage("Null id not allowed");
        verify(clientRepository, times(1)).deleteById(idToDelete);
    }

    @AfterEach
    void tearDown() {
        clientService = null;
        clientRepository = null;
    }
}
