package com.kfedor.dao;

public class UserDao {

    public static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }
    public static UserDao getInstance() {
        return INSTANCE;
    }

}
