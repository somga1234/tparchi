package imt.tparchi.service;

import imt.tparchi.model.Client;
import imt.tparchi.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository; // Simulez le repository

    @InjectMocks
    private ClientService clientService; // Injectez les mocks dans le service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisez les annotations Mockito
    }

    @Test
    void findAll_ShouldReturnClients() {
        // Arrange : Configurez le comportement simulé
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        when(clientRepository.findAll()).thenReturn(List.of(client));

        // Act : Appelez la méthode à tester
        List<Client> clients = clientService.findAll();

        // Assert : Vérifiez les résultats
        assertEquals(1, clients.size());
        assertEquals("John Doe", clients.get(0).getName());
    }

    @Test
    void createClient_ShouldThrowException_WhenClientExists() {
        // Arrange
        Client client = new Client();
        client.setName("John Doe");
        when(clientRepository.existsByName("John Doe")).thenReturn(true);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> clientService.createClient(client));
        assertEquals("Client with this name already exists.", exception.getMessage());
    }

    @Test
    void deleteClient_ShouldDeleteClientById() {
        // Arrange
        Long clientId = 1L;

        // Act
        clientService.deleteClient(clientId);

        // Assert
        verify(clientRepository, times(1)).deleteById(clientId); // Vérifiez que la méthode a été appelée une fois
    }
}