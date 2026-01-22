package com.company;
import com.company.controllers.interfaces.IAdminController;
import com.company.controllers.interfaces.IClientController;
import com.company.controllers.interfaces.IMembershipController;
import com.company.controllers.interfaces.ISubscriptionController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final IAdminController adminController;
    private final IClientController clientController;
    private final IMembershipController membershipController;
    private final ISubscriptionController subscriptionController;

    private Integer currentClientId = null;

    public MyApplication(IAdminController adminController,
                         IClientController clientController,
                         IMembershipController membershipController,
                         ISubscriptionController subscriptionController) {
        this.adminController = adminController;
        this.clientController = clientController;
        this.membershipController = membershipController;
        this.subscriptionController = subscriptionController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to Gym Management Application!");
        System.out.println("1. Login as ADMIN");
        System.out.println("2. Enter as CLIENT");
        System.out.println("0. Exit");
        System.out.print("Enter option (0-2): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        if (adminLoginMenu()) {
                            adminFlow();
                        }
                        break;
                    case 2:
                        clientFlow();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("*************************");
        }
    }

    private boolean adminLoginMenu(){
        System.out.println("\n--- ADMIN LOGIN ---");
        System.out.print("Username:");
        String username = scanner.next();
        System.out.print("Password:");
        String password = scanner.next();

        boolean ok = adminController.login(username, password);
        if (!ok) {
            System.out.println("Wrong username / password!");
        }
        else {
            System.out.println("Admin login successful!");
        }
        return ok;
    }

    private void adminMenu() {
        System.out.println();
        System.out.println("=== ADMIN MENU ===");
        System.out.println("1. Get all clients");
        System.out.println("2. Get client by id");
        System.out.println("3. Create client");
        System.out.println("4. Get all memberships");
        System.out.println("5. Get membership by id");
        System.out.println("6. Create membership");
        System.out.println("7. Subscribe client to membership");
        System.out.println("8. Show all subscriptions");
        System.out.println("9. Enter the gym");
        System.out.println("0. Logout");
        System.out.print("Enter option (0-9): ");
    }

    private void adminFlow() {
        while (true) {
            adminMenu();
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
                    case 9: getCheckInMenu(); break;
                    case 0: return;
                    default: System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("*************************");
        }
    }

    private void clientFlow() {
        while (true) {

            if (currentClientId == null) {
                System.out.println();
                System.out.println("=== CLIENT AUTH ===");
                System.out.println("1. Register");
                System.out.println("2. Login by phone");
                System.out.println("0. Back");
                System.out.print("Enter option (0-2): ");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        createClientMenu();
                        break;
                    case 2:
                        System.out.print("Enter phone: ");
                        String phone = scanner.next();

                        Integer id = clientController.loginClient(phone);
                        if (id == null) {
                            System.out.println("Client not found. Please register first.");
                        } else {
                            currentClientId = id;
                            System.out.println("Logged in! Your client ID: " + currentClientId);
                        }
                        break;
                    case 0:
                        currentClientId = null;
                        return;
                    default:
                        System.out.println("Invalid option!");
                }

                System.out.println("*************************");
                continue;
            }

            System.out.println();
            System.out.println("=== CLIENT ACCOUNT ===");
            System.out.println("1. View all memberships");
            System.out.println("2. Subscribe to membership");
            System.out.println("3. Enter the gym");
            System.out.println("9. Logout");
            System.out.println("0. Back");
            System.out.print("Enter option: ");

            int accOption = scanner.nextInt();

            switch (accOption) {
                case 1:
                    getAllMembershipsMenu();
                        break;
                case 2:
                    System.out.print("Enter membership ID: ");
                    int membershipId = scanner.nextInt();
                    System.out.println(subscriptionController.createSubscription(currentClientId, membershipId));
                    break;
                case 3:
                    System.out.println(subscriptionController.checkIn(currentClientId));
                    break;
                case 9:
                    currentClientId = null;
                    System.out.println("Logged out.");
                    break;
                case 0:
                    currentClientId = null;
                    return;
                default:
                    System.out.println("Invalid option!");
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
    private void getCheckInMenu(){
        System.out.println("Enter client ID:");
        int clientId=scanner.nextInt();
        String response =subscriptionController.checkIn(clientId);
        System.out.println(response);
    }
}