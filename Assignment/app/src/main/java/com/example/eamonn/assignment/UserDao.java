package com.example.eamonn.assignment;

import java.util.List;

public interface UserDao {

    void insert(User user);
    void update(User user);
    void delete(User user);
    List<User> getAll();
}
