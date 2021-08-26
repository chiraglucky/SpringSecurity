package com.multipleauthentication.Model;

import javax.persistence.*;

@Entity
@Table(name = "secret_key")
public class UserSecretKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String pin;

    public UserSecretKey() {
        super();
    }

    public UserSecretKey(int id, String username, String pin) {
        this.id = id;
        this.username = username;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String key) {
        this.pin = key;
    }

    @Override
    public String toString() {
        return "UserSecretKey{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", key='" + pin + '\'' +
                '}';
    }
}
