package com.example.pocketoverflow.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketoverflow.R;
import com.example.pocketoverflow.sortingHat.SortingHatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.codecool.pocketoverflow.name";
    public static final String EXTRA_password = "com.codecool.pocketoverflow.password";
    @BindView(R.id.editName)
    EditText name;
    @BindView(R.id.editPassword)
    EditText password;
    @BindView(R.id.sortingHatButton)
    Button sortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, SortingHatActivity.class);
                intent.putExtra(EXTRA_NAME, name.getText().toString());
                intent.putExtra(EXTRA_password, password.getText().toString());
                startActivity(intent);
            }
        });
    }
}