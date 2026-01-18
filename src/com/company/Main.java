package com.company;
import com.company.controllers.ClientController;
import com.company.controllers.MembershipController;
import com.company.controllers.SubscriptionController;
import com.company.controllers.interfaces.IClientController;
import com.company.controllers.interfaces.IMembershipController;
import com.company.controllers.interfaces.ISubscriptionController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.ClientRepository;
import com.company.repositories.MembershipRepository;
import com.company.repositories.SubscriptionRepository;
import com.company.repositories.interfaces.IClientRepository;
import com.company.repositories.interfaces.IMembershipRepository;
import com.company.repositories.interfaces.ISubscriptionRepository;
public class Main {

    public static void main(String[] args) {
        // Here you specify which DB and UserRepository to use
        // And changing DB should not affect to whole code
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "mydatabase");
        IClientRepository clientRepo = new ClientRepository(db);
        IMembershipRepository membershipRepo = new MembershipRepository(db);
        ISubscriptionRepository subscriptionRepo = new SubscriptionRepository(db);
        IClientController clientController = new ClientController(clientRepo);
        IMembershipController membershipController = new MembershipController(membershipRepo);
        ISubscriptionController subscriptionController = new SubscriptionController(subscriptionRepo, clientRepo, membershipRepo);
        MyApplication app = new MyApplication(clientController, membershipController, subscriptionController);
        app.start();
        db.close();
    }
}