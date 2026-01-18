package com.company.controllers.interfaces;
public interface ISubscriptionController {
    String createSubscription(int clientId, int membershipId);
    String getAllSubscriptions();
}