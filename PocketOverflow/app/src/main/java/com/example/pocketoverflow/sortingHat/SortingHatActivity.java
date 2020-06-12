package com.example.pocketoverflow.sortingHat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketoverflow.R;
import com.example.pocketoverflow.registration.RegistrationActivity;

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

    public static final String EXTRA_HOUSE = "com.codecool.pocketoveflow.house";
    @BindView(R.id.enroll)
    Button enroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_hat);
        ButterKnife.bind(this);
        setHouse();
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SortingHatActivity.this, RegistrationActivity.class);
                intent.putExtra(EXTRA_HOUSE, house.getText());
                startActivity(intent);
            }
        });

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

        Toast.makeText(this, "You have been accepted at Hogwarts School of Witchcraft and Wizardry.", Toast.LENGTH_SHORT).show();
    }
}