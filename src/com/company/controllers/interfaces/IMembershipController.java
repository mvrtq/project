package com.company.controllers.interfaces;
public interface IMembershipController {
    String createMembership(String name, int durationMonths, double price);
    String getMembership(int id);
    String getAllMemberships();
}