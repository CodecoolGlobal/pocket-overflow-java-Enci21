package com.example.pocketoverflow.signIn.ui.home;

public class HomePresenter implements HomeContract.Presenter {

    HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

}