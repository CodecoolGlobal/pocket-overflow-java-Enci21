package com.example.pocketoverflow.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketoverflow.R;
import com.example.pocketoverflow.roomDB.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity implements SignInContract.SignInView {

    public static final String EXTRA_NAME = "com.codecool.pocketoverflow.name";
    public static final String EXTRA_HOUSE = "com.codecool.pocketoverflow.house";
    @BindView(R.id.editTextName)
    EditText nameEditText;
    @BindView(R.id.editTextPassword)
    EditText passwordEditText;
    @BindView(R.id.alohomoraButton)
    Button alohomora;
    SignInPresenter presenter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
        presenter = new SignInPresenter(this, getApplication());
    }

    public void signIn(View view) {
        String username = nameEditText.getText().toString();
        presenter.getUserByUsername(username);
        //runnable spinner kb 1000 milisec
        if (user == null) {
            Toast.makeText(this, "Something went wrong, please try again later!", Toast.LENGTH_SHORT).show();
        } else {
            if (user.getPassword().equals(passwordEditText.getText().toString())) {
                Toast.makeText(this, "Welcome " + username + "!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, MeActivity.class);
                intent.putExtra(EXTRA_NAME, nameEditText.getText().toString());
                intent.putExtra(EXTRA_HOUSE, user.getHouse());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Username or password is wrong!", Toast.LENGTH_SHORT).show();
                nameEditText.setHighlightColor(getResources().getColor(R.color.colorRed));
                passwordEditText.setHighlightColor(getResources().getColor(R.color.colorRed));
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}