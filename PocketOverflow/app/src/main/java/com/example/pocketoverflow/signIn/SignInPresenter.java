package com.example.pocketoverflow.signIn;

import android.app.Application;
import android.os.AsyncTask;

import com.example.pocketoverflow.roomDB.User;
import com.example.pocketoverflow.roomDB.UserRepository;

public class SignInPresenter implements SignInContract.SignInPresenter {

    SignInContract.SignInView view;
    UserRepository userRepository;

    public SignInPresenter(SignInContract.SignInView view, Application app) {
        this.view = view;
        userRepository = new UserRepository(app);
    }

    public void getUserByUsername(String username) {
        new GetUserAsyncTask().execute(username);
    }

    class GetUserAsyncTask extends AsyncTask<String, Void, User> {

        @Override
        protected User doInBackground(String... strings) {
            return userRepository.getUserByUsername(strings[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            view.setUser(user);
        }
    }
}
