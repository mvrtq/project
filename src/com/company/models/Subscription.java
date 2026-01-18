package com.company.models;

import java.time.LocalDate;

public class Subscription {
    private int id;
    private int clientId;
    private int membershipId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Subscription() {
    }

    public Subscription(int clientId, int membershipId, LocalDate startDate, LocalDate endDate) {
        setClientId(clientId);
        setMembershipId(membershipId);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Subscription(int id, int clientId, int membershipId, LocalDate startDate, LocalDate endDate) {
        this(clientId, membershipId, startDate, endDate);
        setId(id);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMembershipId() {
        return membershipId;
    }
    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", membershipId=" + membershipId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}