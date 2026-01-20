package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Client;
import com.company.repositories.interfaces.IClientRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClientRepository implements IClientRepository {
    private final IDB db;

    public ClientRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createClient(Client client) {
        Connection con = null;
        try {
            con = db.getConnection();
            String checkSql = "SELECT id FROM clients WHERE phone=?";
            PreparedStatement checkSt = con.prepareStatement(checkSql);
            checkSt.setString(1, client.getPhone());
            ResultSet rsCheck = checkSt.executeQuery();
            if (rsCheck.next()) {
                System.out.println("Client with this phone already exists!");
                return false;
            }
            String sql = "INSERT INTO clients(name, surname, phone) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, client.getName());
            st.setString(2, client.getSurname());
            st.setString(3, client.getPhone());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Client getClient(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id, name, surname, phone FROM clients WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone"));
                return client;
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id, name, surname, phone FROM clients";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Client> clients = new ArrayList<>();
            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone"));
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Integer getClientIdByPhone(String phone){
        try (Connection con = db.getConnection()){
            String sql = "SELECT id FROM clients WHERE phone = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, phone);
            ResultSet rs = st.executeQuery();
            if (rs.next()) return rs.getInt("id");
            return null;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}