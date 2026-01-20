package com.company.controllers.interfaces;
public interface IClientController {
    String createClient(String name, String surname, String phone);
    String getClient(int id);
    String getAllClients();
    Integer loginClient(String phone);
}