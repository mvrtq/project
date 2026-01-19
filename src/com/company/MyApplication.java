package com.company;
import com.company.controllers.interfaces.IClientController;
import com.company.controllers.interfaces.IMembershipController;
import com.company.controllers.interfaces.ISubscriptionController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final IClientController clientController;
    private final IMembershipController membershipController;
    private final ISubscriptionController subscriptionController;
    public MyApplication(IClientController clientController,
                         IMembershipController membershipController,
                         ISubscriptionController subscriptionController) {
        this.clientController = clientController;
        this.membershipController = membershipController;
        this.subscriptionController = subscriptionController;
    }
    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to Gym Management Application!");
        System.out.println("Select option:");
        System.out.println("1. Get all clients");
        System.out.println("2. Get client by id");
        System.out.println("3. Create client");
        System.out.println("4. Get all memberships");
        System.out.println("5. Get membership by id");
        System.out.println("6. Create membership");
        System.out.println("7. Subscribe client to membership");
        System.out.println("8. Show all subscriptions");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-8): ");
    }
    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1: getAllClientsMenu(); break;
                    case 2: getClientByIdMenu(); break;
                    case 3: createClientMenu(); break;
                    case 4: getAllMembershipsMenu(); break;
                    case 5: getMembershipByIdMenu(); break;
                    case 6: createMembershipMenu(); break;
                    case 7: subscribeClientMenu(); break;
                    case 8: getAllSubscriptionsMenu(); break;
                    case 0: return;
                    default: System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer: " + e);
                scanner.nextLine(); // clear invalid input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("*************************");
        }
    }
    private void getAllClientsMenu() {
        String response = clientController.getAllClients();
        System.out.println(response);
    }
    private void getClientByIdMenu() {
        System.out.println("Please enter client ID:");
        int id = scanner.nextInt();
        String response = clientController.getClient(id);
        System.out.println(response);
    }
    private void createClientMenu() {
        System.out.println("Please enter client's first name:");
        String name = scanner.next();
        System.out.println("Please enter client's last name:");
        String surname = scanner.next();
        System.out.println("Please enter client's phone number:");
        String phone = scanner.next();
        String response = clientController.createClient(name, surname, phone);
        System.out.println(response);
    }
    private void getAllMembershipsMenu() {
        String response = membershipController.getAllMemberships();
        System.out.println(response);
    }
    private void getMembershipByIdMenu() {
        System.out.println("Please enter membership ID:");
        int id = scanner.nextInt();
        String response = membershipController.getMembership(id);
        System.out.println(response);
    }
    private void createMembershipMenu() {
        System.out.println("Please enter membership name:");
        String name = scanner.next();
        System.out.println("Please enter duration in months:");
        int duration = scanner.nextInt();
        System.out.println("Please enter price:");
        double price = scanner.nextDouble();
        String response = membershipController.createMembership(name, duration, price);
        System.out.println(response);
    }
    private void subscribeClientMenu() {
        System.out.println("Enter client ID:");
        int clientId = scanner.nextInt();
        System.out.println("Enter membership ID:");
        int membershipId = scanner.nextInt();
        String response = subscriptionController.createSubscription(clientId, membershipId);
        System.out.println(response);
    }
    private void getAllSubscriptionsMenu() {
        String response = subscriptionController.getAllSubscriptions();
        System.out.println(response);
    }
}