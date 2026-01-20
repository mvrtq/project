package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.repositories.interfaces.IAdminRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepository implements IAdminRepository {
    private final IDB db;

    public AdminRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean login(String username, String password) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT 1 FROM admins WHERE username=? AND password=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Admin login error: " + e.getMessage());
            return false;
        }
    }
}
