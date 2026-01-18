package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Membership;
import com.company.repositories.interfaces.IMembershipRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipRepository implements IMembershipRepository {

    private final IDB db;

    public MembershipRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createMembership(Membership membership) {
        Connection con = null;
        try {
            con = db.getConnection();


            String checkSql = "SELECT id FROM memberships WHERE name=?";
            PreparedStatement checkSt = con.prepareStatement(checkSql);
            checkSt.setString(1, membership.getName());
            ResultSet rsCheck = checkSt.executeQuery();

            if (rsCheck.next()) {
                System.out.println("Membership with this name already exists!");
                return false;
            }


            String sql = "INSERT INTO memberships(name, duration_months, price) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, membership.getName());
            st.setInt(2, membership.getDurationMonths());
            st.setDouble(3, membership.getPrice());
            st.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Membership getMembership(int id) {
        try (Connection con = db.getConnection()) {

            String sql = "SELECT id, name, duration_months, price FROM memberships WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Membership(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("duration_months"),
                        rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Membership> getAllMemberships() {
        List<Membership> list = new ArrayList<>();

        try (Connection con = db.getConnection()) {

            String sql = "SELECT id, name, duration_months, price FROM memberships";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Membership(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("duration_months"),
                        rs.getDouble("price")
                ));
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return list;
    }
}