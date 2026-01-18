package com.company.models;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String phone;

    public Client() {
    }

    public Client(String name, String surname, String phone) {
        setName(name);
        setSurname(surname);
        setPhone(phone);
    }

    public Client(int id, String name, String surname, String phone) {
        this(name, surname, phone);
        setId(id);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
