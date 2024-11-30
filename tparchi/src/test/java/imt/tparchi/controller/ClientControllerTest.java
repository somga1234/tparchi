package imt.tparchi.controller;

import imt.tparchi.model.Client;
import imt.tparchi.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void getClients_ShouldReturnListOfClients() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");

        when(clientService.findAll()).thenReturn(List.of(client));

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void createClient_ShouldReturnCreatedClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");

        when(clientService.createClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
