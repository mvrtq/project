package com.company.controllers;
import com.company.controllers.interfaces.IMembershipController;
import com.company.models.Membership;
import com.company.repositories.interfaces.IMembershipRepository;
import java.util.List;
public class MembershipController implements IMembershipController {
    private final IMembershipRepository repo;
    public MembershipController(IMembershipRepository repo) {
        this.repo = repo;
    }
    @Override
    public String createMembership(String name, int durationMonths, double price) {
        if (durationMonths <= 0) {
            return "ERROR: Membership duration must be greater than 0 months!";
        }

        if (price <= 0) {
            return "ERROR: Membership price must be greater than 0!";
        }
        Membership m = new Membership(name, durationMonths, price);
        boolean created = repo.createMembership(m);
        return (created ? "Membership was created!" : "Membership creation was failed!");
    }
    @Override
    public String getMembership(int id) {
        Membership m = repo.getMembership(id);
        return (m == null ? "Membership was not found!" : m.toString());
    }
    @Override
    public String getAllMemberships() {
        List<Membership> memberships = repo.getAllMemberships();
        StringBuilder response = new StringBuilder();
        for (Membership m : memberships) {
            response.append(m.toString()).append("\n");
        }
        return response.toString();
    }
}