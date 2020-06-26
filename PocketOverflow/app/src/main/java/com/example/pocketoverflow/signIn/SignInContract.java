package com.example.pocketoverflow.signIn;

import android.view.View;

import com.example.pocketoverflow.BasePresenter;
import com.example.pocketoverflow.BaseView;
import com.example.pocketoverflow.roomDB.User;

public interface SignInContract {

    interface SignInView extends BaseView {

        void signIn(View view);

        void showLoading();

        void hideLoading();

        void setUser(User user);
    }

    interface SignInPresenter<V> extends BasePresenter<V> {

    }
}
