package com.company.repositories.interfaces;

import com.company.models.Client;
import java.util.List;

public interface IClientRepository {
    boolean createClient(Client client);
    Client getClient(int id);
    List<Client> getAllClients();
    Integer getClientIdByPhone(String phone);
}
