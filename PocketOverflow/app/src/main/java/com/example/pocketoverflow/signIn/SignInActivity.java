package com.example.pocketoverflow.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketoverflow.R;
import com.example.pocketoverflow.roomDB.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity implements SignInContract.SignInView {

    public static final String EXTRA_USER = "com.codecool.pocketoverflow.user";

    @BindView(R.id.editTextName)
    EditText nameEditText;

    @BindView(R.id.editTextPassword)
    EditText passwordEditText;

    @BindView(R.id.alohomoraButton)
    Button alohomora;

    @BindView(R.id.frameGrayoverlay)
    FrameLayout layout;

    @BindView(R.id.loading)
    ProgressBar progressBar;

    SignInPresenter presenter;
    User user;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
        presenter = new SignInPresenter(this, getApplication());
    }

    public void signIn(View view) {
        username = nameEditText.getText().toString();
        presenter.getUserByUsername(username);
        showLoading();

        if (user == null) {
            hideLoading();
            Toast.makeText(this, "Something went wrong, please try again later!", Toast.LENGTH_SHORT).show();
        } else {
            if (user.getPassword().equals(passwordEditText.getText().toString())) {
                hideLoading();
                Intent intent = new Intent(SignInActivity.this, MeActivity.class);
                intent.putExtra(EXTRA_USER, user);
                startActivity(intent);
            } else {
                hideLoading();
                Toast.makeText(this, "Username or password is wrong!", Toast.LENGTH_SHORT).show();
                nameEditText.setHighlightColor(getResources().getColor(R.color.colorRed));
                passwordEditText.setHighlightColor(getResources().getColor(R.color.colorRed));
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showLoading() {
        layout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        layout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}