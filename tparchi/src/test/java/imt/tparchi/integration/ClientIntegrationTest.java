package imt.tparchi.integration;

import imt.tparchi.model.Client;
import imt.tparchi.repository.ClientRepository;
import imt.tparchi.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ClientIntegrationTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void createAndRetrieveClient_ShouldWork() {
        // Arrange
        Client client = new Client();
        client.setName("Integration Test Client");

        // Act
        client = clientService.createClient(client);
        assertNotNull(client.getId());

        // Assert
        List<Client> clients = clientService.findAll();
        assertEquals(1, clients.size());
        assertEquals("Integration Test Client", clients.get(0).getName());
    }

    @Test
    void deleteClient_ShouldRemoveClientFromDB() {
        // Arrange
        Client client = new Client();
        client.setName("Client To Delete");
        client = clientService.createClient(client);

        // Act
        clientService.deleteClient(client.getId());

        // Assert
        List<Client> clients = clientRepository.findAll();
        assertTrue(clients.isEmpty());
    }
}
