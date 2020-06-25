package com.example.pocketoverflow.sortingHat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketoverflow.R;
import com.example.pocketoverflow.registration.RegistrationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SortingHatActivity extends AppCompatActivity implements SortingHatContract.SortingHatView {

    @BindView(R.id.house)
    TextView house;

    public static final String EXTRA_HOUSE = "com.codecool.pocketoveflow.house";
    @BindView(R.id.enroll)
    Button enroll;

    @BindView(R.id.sortingHatImage)
    ImageView sortingHatImage;

    @BindView(R.id.textViewHat)
    TextView hatSaid;

    @BindView(R.id.loading)
    ProgressBar loading;

    @BindView(R.id.frameGrayoverlay)
    FrameLayout frameLayout;
    SortingHatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_hat);
        ButterKnife.bind(this);
        presenter = new SortingHatPresenter(this, getApplication());

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SortingHatActivity.this, RegistrationActivity.class);
                intent.putExtra(EXTRA_HOUSE, house.getText());
                startActivity(intent);
            }
        });
        if (savedInstanceState == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showLoading();
                    hideLoading();
                }
            }, 2000);
        } else {
            hideLoading();
            house.setText(savedInstanceState.getString("HOUSE"));
        }

    }


    public void register(View view) {
        Toast.makeText(this, "You have been accepted at Hogwarts School of Witchcraft and Wizardry.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setHouse(String house) {
        this.house.setText(house);
    }

    public void showLoading() {
        enroll.setVisibility(View.INVISIBLE);
        house.setVisibility(View.INVISIBLE);
        frameLayout.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        hatSaid.setVisibility(View.VISIBLE);
        sortingHatImage.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        enroll.setVisibility(View.VISIBLE);
        house.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
        hatSaid.setVisibility(View.INVISIBLE);
        sortingHatImage.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("HOUSE", house.getText().toString());
    }
}