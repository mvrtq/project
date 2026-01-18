package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Subscription;
import com.company.repositories.interfaces.ISubscriptionRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRepository implements ISubscriptionRepository {

    private final IDB db;

    public SubscriptionRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createSubscription(Subscription subscription) {
        try (Connection con = db.getConnection()) {

            String sql =
                    "INSERT INTO subscriptions(client_id, membership_id, start_date, end_date) " +
                            "VALUES (?, ?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, subscription.getClientId());
            st.setInt(2, subscription.getMembershipId());
            st.setDate(3, Date.valueOf(subscription.getStartDate()));
            st.setDate(4, Date.valueOf(subscription.getEndDate()));
            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Subscription getSubscription(int id) {
        try (Connection con = db.getConnection()) {

            String sql =
                    "SELECT id, client_id, membership_id, start_date, end_date " +
                            "FROM subscriptions WHERE id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Subscription(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("membership_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                );
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        List<Subscription> list = new ArrayList<>();

        try (Connection con = db.getConnection()) {

            String sql =
                    "SELECT id, client_id, membership_id, start_date, end_date FROM subscriptions";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Subscription(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("membership_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return list;
    }

    @Override
    public boolean clientHasActiveSubscription(int clientId) {
        try (Connection con = db.getConnection()) {

            String sql =
                    "SELECT id FROM subscriptions " +
                            "WHERE client_id = ? AND end_date >= CURRENT_DATE";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, clientId);
            ResultSet rs = st.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
}