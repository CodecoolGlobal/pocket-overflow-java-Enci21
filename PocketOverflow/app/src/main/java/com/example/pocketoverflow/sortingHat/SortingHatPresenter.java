package com.example.pocketoverflow.sortingHat;

import android.app.Application;

import com.example.pocketoverflow.roomDB.User;
import com.example.pocketoverflow.roomDB.UserRepository;

public class SortingHatPresenter implements SortingHatContract.Presenter {

    SortingHatContract.View view;
    UserRepository repository;

    public SortingHatPresenter(SortingHatContract.View view, Application app) {

        repository = new UserRepository(app);
        this.view = view;
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }
}
