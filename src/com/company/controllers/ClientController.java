package com.company.controllers;
import com.company.controllers.interfaces.IClientController;
import com.company.models.Client;
import com.company.repositories.interfaces.IClientRepository;
import java.util.List;
public class ClientController implements IClientController {
    private final IClientRepository repo;
    public ClientController(IClientRepository repo) {
        this.repo = repo;
    }
    @Override
    public String createClient(String name, String surname, String phone) {
        if (phone.length()!=11){
            return "ERROR: Phone nubmer must be 11 digits";
        }
        Client client = new Client(name, surname, phone);
        boolean created = repo.createClient(client);
        return (created ? "Client was created!" : "Client creation was failed!");
    }
    @Override
    public String getClient(int id) {
        Client client = repo.getClient(id);
        return (client == null ? "Client was not found!" : client.toString());
    }
    @Override
    public String getAllClients() {
        List<Client> clients = repo.getAllClients();
        StringBuilder response = new StringBuilder();
        for (Client c : clients) {
            response.append(c.toString()).append("\n");
        }
        return response.toString();
    }
}
