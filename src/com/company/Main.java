package com.company;

import com.company.controllers.ClientController;
import com.company.controllers.interfaces.IUserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.ClientRepository;
import com.company.repositories.interfaces.IClientRepository;

public class Main {

    public static void main(String[] args) {
        // Here you specify which DB and UserRepository to use
        // And changing DB should not affect to whole code
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "mydatabase");
        IClientRepository repo = new ClientRepository(db);
        IUserController controller = new ClientController(repo);

        MyApplication app = new MyApplication(controller);


        app.start();

        db.close(); //
    }
}

