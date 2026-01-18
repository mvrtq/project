package com.company.controllers;
import com.company.controllers.interfaces.ISubscriptionController;
import com.company.models.Client;
import com.company.models.Membership;
import com.company.models.Subscription;
import com.company.repositories.interfaces.IClientRepository;
import com.company.repositories.interfaces.IMembershipRepository;
import com.company.repositories.interfaces.ISubscriptionRepository;
import java.time.LocalDate;
import java.util.List;
public class SubscriptionController implements ISubscriptionController {
    private final ISubscriptionRepository subRepo;
    private final IClientRepository clientRepo;
    private final IMembershipRepository membershipRepo;
    public SubscriptionController(ISubscriptionRepository subRepo,
                                  IClientRepository clientRepo,
                                  IMembershipRepository membershipRepo) {
        this.subRepo = subRepo;
        this.clientRepo = clientRepo;
        this.membershipRepo = membershipRepo;
    }
    @Override
    public String createSubscription(int clientId, int membershipId) {
        Client client = clientRepo.getClient(clientId);
        if (client == null) {
            return "Client with ID " + clientId + " was not found!";
        }
        Membership plan = membershipRepo.getMembership(membershipId);
        if (plan == null) {
            return "Membership with ID " + membershipId + " was not found!";
        }
        if (subRepo.clientHasActiveSubscription(clientId)) {
            return "Failed: Client already has an active subscription!";
        }
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(plan.getDurationMonths());
        Subscription sub = new Subscription(clientId, membershipId, startDate, endDate);
        boolean created = subRepo.createSubscription(sub);
        return (created ? "Subscription was created!" : "Subscription creation was failed!");
    }
    @Override
    public String getAllSubscriptions() {
        List<Subscription> subscriptions = subRepo.getAllSubscriptions();
        StringBuilder response = new StringBuilder();
        for (Subscription sub : subscriptions) {
            Client c = clientRepo.getClient(sub.getClientId());
            Membership plan = membershipRepo.getMembership(sub.getMembershipId());
            response.append("Subscription{");
            response.append("id=").append(sub.getId());
            response.append(", Client=").append(c.getName()).append(" ").append(c.getSurname());
            response.append(", Membership=").append(plan.getName());
            response.append(", StartDate=").append(sub.getStartDate());
            response.append(", EndDate=").append(sub.getEndDate());
            response.append("}\n");
        }
        return response.toString();
    }
}
