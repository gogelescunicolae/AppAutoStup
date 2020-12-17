package com.example.stup.ui.firebaseClass;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class UserMod implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;
    private String username;
    private String password;
    static int countUser=0;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserMod(String username, String password) {
        this.username = username;
        this.password = password;
        countUser++;
    }

    public static int getCountUser() {
        return countUser;
    }

    public static void setCountUser(int countUser) {
        UserMod.countUser = countUser;
    }

    @Override
    public String toString() {
        return "UserModel1{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
