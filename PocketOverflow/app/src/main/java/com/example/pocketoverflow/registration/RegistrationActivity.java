package com.example.pocketoverflow.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketoverflow.R;
import com.example.pocketoverflow.roomDB.User;
import com.example.pocketoverflow.signIn.SignInActivity;
import com.example.pocketoverflow.sortingHat.SortingHatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {


    @BindView(R.id.editName)
    EditText name;

    @BindView(R.id.editPassword)
    EditText password;

    @BindView(R.id.myHouse)
    TextView myHouse;

    @BindView(R.id.patronusDropdown)
    Spinner patronusDropdown;

    @BindView(R.id.switchAnimagus)
    Switch switchAnimagus;

    @BindView(R.id.animagusAnimalEditText)
    EditText animalAnimagus;

    @BindView(R.id.switchDA)
    Switch switchDA;

    @BindView(R.id.switchOF)
    Switch switchOF;

    @BindView(R.id.sortingHatButton)
    Button sortButton;

    RegistrationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        presenter = new RegistrationPresenter(this, getApplication());

        Intent intent = getIntent();
        myHouse.setText(intent.getStringExtra(SortingHatActivity.EXTRA_HOUSE).replace("\"", ""));

        String[] options = {"Cat", "Dog", "Lion", "Squirrel", "Otter", "Tiger", "Pig", "Rabbit", "Roe", "Deer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patronusDropdown.setAdapter(adapter);


        switchAnimagus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchAnimagus.isChecked()) {
                    animalAnimagus.setVisibility(View.VISIBLE);
                } else {
                    animalAnimagus.setVisibility(View.GONE);
                }
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") ||
                        password.getText().toString().equals("") ||
                        switchAnimagus.isChecked() && animalAnimagus.getText().toString().equals("")) {
                    Toast.makeText(RegistrationActivity.this, "You have to fill every field!", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(
                            name.getText().toString(),
                            password.getText().toString(),
                            myHouse.getText().toString(),
                            patronusDropdown.getSelectedItem().toString(),
                            animalAnimagus.getText().toString(),
                            switchDA.isChecked(),
                            switchOF.isChecked());
                    presenter.insertUser(user);
                    Intent intent = new Intent(RegistrationActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}