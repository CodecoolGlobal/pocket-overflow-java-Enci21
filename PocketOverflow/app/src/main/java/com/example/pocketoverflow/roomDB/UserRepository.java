package com.example.pocketoverflow.roomDB;

import android.app.Application;

public class UserRepository {

    UserDao userDao;

    public UserRepository(Application app) {
        UserDatabase db = UserDatabase.getDb(app);
        userDao = db.dao();
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public void insertUser(User user) {
        UserDatabase.databaseWithExecutor.execute(() -> {
            userDao.registration(user);
        });
    }
}
