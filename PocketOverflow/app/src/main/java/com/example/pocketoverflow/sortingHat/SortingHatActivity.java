package com.example.pocketoverflow.sortingHat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketoverflow.R;
import com.example.pocketoverflow.registration.RegistrationActivity;
import com.example.pocketoverflow.roomDB.User;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SortingHatActivity extends AppCompatActivity implements SortingHatContract.View {

    @BindView(R.id.house)
    TextView house;

    private String name;

    private String password;

    private SortingHatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_hat);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        presenter = new SortingHatPresenter(this, getApplication());
        name = intent.getStringExtra(RegistrationActivity.EXTRA_NAME);
        password = intent.getStringExtra(RegistrationActivity.EXTRA_password);
        setHouse();
    }

   /* public void showLoading(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.showDestinations(destinations);
                view.emptyDatabaseMessageShow();
            }
        }, 2000);
    }*/

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
                    String houseString = response.body().string();

                    SortingHatActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            house.setText(houseString);
                        }
                    });
                }
            }
        });
    }

    public void register(View view) {
        User user = new User(name, password, house.getText().toString());
        presenter.insertUser(user);
        Toast.makeText(this, "You have been accepted at Hogwarts School of Witchcraft and Wizardry.", Toast.LENGTH_SHORT).show();
    }
}