package com.company.repositories.interfaces;

import com.company.models.Subscription;
import java.util.List;

public interface ISubscriptionRepository {
    boolean createSubscription(Subscription subscription);
    Subscription getSubscription(int id);
    List<Subscription> getAllSubscriptions();
    boolean clientHasActiveSubscription(int clientId);
}

