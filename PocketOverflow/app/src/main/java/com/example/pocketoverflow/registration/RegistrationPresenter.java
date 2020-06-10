package com.example.pocketoverflow.registration;

import android.app.Application;

import com.example.pocketoverflow.roomDB.User;
import com.example.pocketoverflow.roomDB.UserRepository;

public class RegistrationPresenter implements RegistrationContract.Presenter {


    RegistrationContract.View view;
    UserRepository repository;

    public RegistrationPresenter(RegistrationContract.View view, Application app) {
        repository = new UserRepository(app);
        this.view = view;
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }
}
