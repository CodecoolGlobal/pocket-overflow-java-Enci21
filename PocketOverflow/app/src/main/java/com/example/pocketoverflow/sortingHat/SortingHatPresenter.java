package com.example.pocketoverflow.sortingHat;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.example.pocketoverflow.roomDB.UserRepository;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SortingHatPresenter implements SortingHatContract.SortingHatPresenter {

    SortingHatContract.SortingHatView view;
    UserRepository repository;

    public SortingHatPresenter(SortingHatContract.SortingHatView view, Application app) {
        this.view = view;
        this.repository = new UserRepository(app);
        setHouse();
    }

    public void setHouse() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://www.potterapi.com/v1/sortingHat";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String houseString = response.body().string().replace("\"", "");

                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setHouse(houseString);
                        }
                    }, 2000);
                }
            }
        });

    }
}
