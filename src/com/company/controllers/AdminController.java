package com.company.controllers;

import com.company.controllers.interfaces.IAdminController;
import com.company.repositories.interfaces.IAdminRepository;

public class AdminController implements IAdminController {
    private final IAdminRepository repo;

    public AdminController(IAdminRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean login(String username, String password) {
        return repo.login(username, password);
    }
}

