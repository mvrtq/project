package com.company.repositories.interfaces;

import com.company.models.Membership;
import java.util.List;

public interface IMembershipRepository {
    boolean createMembership(Membership membership);
    Membership getMembership(int id);
    List<Membership> getAllMemberships();
}

